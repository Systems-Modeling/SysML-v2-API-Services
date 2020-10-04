package dao.impl.jpa;

import config.MetamodelProvider;
import dao.ElementDao;
import javabean.JavaBeanHelper;
import jpa.manager.JPAManager;
import org.hibernate.Hibernate;
import org.omg.sysml.internal.CommitIndex;
import org.omg.sysml.internal.impl.CommitIndexImpl;
import org.omg.sysml.internal.impl.CommitIndexImpl_;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.ElementIdentity;
import org.omg.sysml.lifecycle.ElementVersion;
import org.omg.sysml.lifecycle.impl.ElementIdentityImpl;
import org.omg.sysml.lifecycle.impl.ElementIdentityImpl_;
import org.omg.sysml.lifecycle.impl.ElementVersionImpl;
import org.omg.sysml.lifecycle.impl.ElementVersionImpl_;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.impl.MofObjectImpl;
import org.omg.sysml.metamodel.impl.MofObjectImpl_;
import org.omg.sysml.query.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class JpaElementDao extends JpaDao<Element> implements ElementDao {
    // TODO Explore alternative to serializing lazy entity attributes that doesn't involve resolving all proxies one level.
    static UnaryOperator<Element> PROXY_RESOLVER = element -> {
        element = Hibernate.unproxy(element, Element.class);
        JavaBeanHelper.getBeanPropertyValues(element).values().stream()
                .flatMap(o -> o instanceof Collection ? ((Collection<?>) o).stream() : Stream.of(o)).filter(o -> o instanceof Element)
                .map(o -> (Element) o).forEach(Hibernate::unproxy);
        return element;
    };

    private final MetamodelProvider metamodelProvider;

    @Inject
    public JpaElementDao(JPAManager jpaManager, MetamodelProvider metamodelProvider) {
        super(jpaManager);
        this.metamodelProvider = metamodelProvider;
    }

    @Override
    public Optional<Element> findById(UUID id) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<MofObjectImpl> query = builder.createQuery(MofObjectImpl.class);
            Root<MofObjectImpl> root = query.from(MofObjectImpl.class);
            query.select(root).where(builder.and(
                    builder.equal(root.get(MofObjectImpl_.identifier), id),
                    getTypeExpression(builder, root)
            ));
            try {
                return Optional.of((Element) em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Element> findAll() {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<MofObjectImpl> query = builder.createQuery(MofObjectImpl.class);
            Root<MofObjectImpl> root = query.from(MofObjectImpl.class);
            query.select(root).where(getTypeExpression(builder, root));
            return em.createQuery(query).getResultStream().map(o -> (Element) o).collect(Collectors.toList());
        });
    }

    @Override
    public void deleteAll() {
        jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaDelete<MofObjectImpl> query = builder.createCriteriaDelete(MofObjectImpl.class);
            Root<MofObjectImpl> root = query.from(MofObjectImpl.class);
            query.where(getTypeExpression(builder, root));
            return ((Stream<?>) em.createQuery(query).getResultStream()).map(o -> (Element) o).collect(Collectors.toList());
        });
    }

    @Override
    public Set<Element> findAllByCommit(Commit commit) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(metamodelProvider.getImplementationClass(Commit.class), commit.getId());
            return getCommitIndex(c, em).getWorkingElementVersions().stream()
                    .map(ElementVersion::getData)
                    .filter(mof -> mof instanceof Element)
                    .map(mof -> (Element) mof)
                    .map(PROXY_RESOLVER)
                    .collect(Collectors.toSet());
        });
    }

    @Override
    public Optional<Element> findByCommitAndId(Commit commit, UUID id) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(metamodelProvider.getImplementationClass(Commit.class), commit.getId());
            CommitIndex commitIndex = getCommitIndex(c, em);

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<ElementVersionImpl> query = builder.createQuery(ElementVersionImpl.class);
            Root<CommitIndexImpl> commitIndexRoot = query.from(CommitIndexImpl.class);
            SetJoin<CommitIndexImpl, ElementVersionImpl> workingElementVersionsJoin = commitIndexRoot.join(CommitIndexImpl_.workingElementVersions);
            Join<ElementVersionImpl, ElementIdentityImpl> elementIdentityJoin = workingElementVersionsJoin.join(ElementVersionImpl_.identity);
            query.select(workingElementVersionsJoin).where(
                    builder.equal(commitIndexRoot.get(CommitIndexImpl_.id), commitIndex.getId()),
                    builder.equal(elementIdentityJoin.get(ElementIdentityImpl_.id), id)
            );
            try {
                return Optional.of(em.createQuery(query).getSingleResult())
                        .map(ElementVersion::getData)
                        .filter(mof -> mof instanceof Element)
                        .map(mof -> (Element) mof)
                        .map(PROXY_RESOLVER);
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public Set<Element> findRootsByCommit(Commit commit) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(metamodelProvider.getImplementationClass(Commit.class), commit.getId());
            return getCommitIndex(c, em).getWorkingElementVersions().stream()
                    .map(ElementVersion::getData)
                    .filter(mof -> (mof instanceof Element) && !(mof instanceof Relationship))
                    .map(mof -> (Element) mof)
                    .filter(element -> element.getOwner() == null)
                    .map(PROXY_RESOLVER)
                    .collect(Collectors.toSet());
        });
    }

    @Override
    public Set<Element> findByCommitAndQuery(Commit commit, Query query) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(metamodelProvider.getImplementationClass(Commit.class), commit.getId());
            Query q = query.getId() == null || em.contains(query) ? query : em.find(metamodelProvider.getImplementationClass(Query.class), query.getId());
            return getCommitIndex(c, em).getWorkingElementVersions().stream()
                    .filter(scope(q))
                    .map(ElementVersion::getData)
                    .filter(mof -> mof instanceof Element)
                    .map(mof -> (Element) mof)
                    .filter(constrain(q.getWhere()))
                    .map(PROXY_RESOLVER)
                    .collect(Collectors.toSet());
        });
    }

    protected static List<Class<?>> SUPPORTED_PRIMITIVE_CONSTRAINT_CLASSES = Arrays.asList(
            Number.class,
            Boolean.class,
            String.class
    );

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected static Comparator<Object> UNSAFE_COMPARABLE_COMPARATOR = (o1, o2) -> ((Comparable) o1).compareTo(o2);

    protected Predicate<ElementVersion> scope(Query query) {
        if (query.getScope() == null || query.getScope().isEmpty()) {
            return ev -> true;
        }
        return ev -> ev.getIdentity() != null && query.getScope().stream()
                .map(ElementIdentity::getId)
                .anyMatch(id -> Objects.equals(id, ev.getIdentity().getId()));
    }

    protected Predicate<Element> constrain(Constraint constraint) {
        Objects.requireNonNull(constraint);
        if (constraint instanceof PrimitiveConstraint) {
            PrimitiveConstraint primitiveConstraint = (PrimitiveConstraint) constraint;
            return element -> {
                Object actualValue;
                Object constrainedValue;
                switch (primitiveConstraint.getProperty()) {
                    case "@type":
                        try {
                            actualValue = metamodelProvider.getInterface(element.getClass()).getSimpleName();
                        } catch (ClassNotFoundException e) {
                            throw new IllegalStateException(e);
                        }
                        constrainedValue = primitiveConstraint.getValue();
                        break;
                    default:
                        PropertyDescriptor property = JavaBeanHelper.getBeanProperties(element).get(primitiveConstraint.getProperty());
                        if (property == null) {
                            return false;
                        }
                        if (SUPPORTED_PRIMITIVE_CONSTRAINT_CLASSES.stream()
                                .noneMatch(supported -> supported.isAssignableFrom(property.getPropertyType()))) {
                            return false;
                        }
                        actualValue = JavaBeanHelper.getBeanPropertyValue(element, property);
                        constrainedValue = JavaBeanHelper.convert(primitiveConstraint.getValue(), property.getPropertyType());
                        break;
                }
                if (actualValue == null || constrainedValue == null) {
                    return (actualValue == null && constrainedValue == null && Objects.equals(primitiveConstraint.getOperator(), PrimitiveOperator.EQUALS)) != primitiveConstraint.getInverse();
                }
                int comparison = Objects.compare(actualValue, constrainedValue, UNSAFE_COMPARABLE_COMPARATOR);
                boolean comparisonResult;
                switch (primitiveConstraint.getOperator()) {
                    case LESS_THAN:
                        comparisonResult = comparison < 0;
                        break;
                    case EQUALS:
                        comparisonResult = comparison == 0;
                        break;
                    case GREATER_THAN:
                        comparisonResult = comparison > 0;
                        break;
                    default:
                        throw new UnsupportedOperationException("Unsupported primitive constraint operator: " + primitiveConstraint.getOperator().name());
                }
                return comparisonResult != primitiveConstraint.getInverse();
            };
        }
        else if (constraint instanceof CompositeConstraint) {
            CompositeConstraint compositeConstraint = (CompositeConstraint) constraint;
            boolean and = Objects.equals(compositeConstraint.getOperator(), CompositeOperator.AND);
            return compositeConstraint.getConstraint().stream()
                    .map(this::constrain)
                    .reduce((p1, p2) -> and ? p1.and(p2) : p1.or(p2))
                    .orElse(e -> true);
        }
        else {
            throw new IllegalArgumentException("Unknown constraint type: " + constraint.getClass().getSimpleName());
        }
    }

    protected <T> Map<Commit, T> queryCommitTree(Commit commit, Function<Commit, T> query) {
        return queryCommitTree(commit, query, t -> false);
    }

    protected <T> Map<Commit, T> queryCommitTree(Commit commit, Function<Commit, T> query, java.util.function.Predicate<T> terminationCondition) {
        Map<Commit, T> results = new LinkedHashMap<>();
        Commit currentCommit = commit;
        Set<Commit> visitedCommits = new HashSet<>();
        while (currentCommit != null && !visitedCommits.contains(currentCommit)) {
            T t = query.apply(currentCommit);
            results.put(currentCommit, t);
            if (terminationCondition.test(t)) {
                break;
            }
            visitedCommits.add(currentCommit);
            currentCommit = currentCommit.getPreviousCommit();
        }
        return results;
    }

    protected Stream<ElementVersion> streamWorkingElementVersions(Commit commit) {
        Set<UUID> visitedElements = ConcurrentHashMap.newKeySet();
        Map<Commit, Stream<ElementVersion>> results = queryCommitTree(commit,
                c -> c.getChange().stream()
                        .filter(record -> record.getIdentity() != null && record.getIdentity().getId() != null && record.getData() != null)
                        .filter(record -> !visitedElements.contains(record.getIdentity().getId()))
                        .peek(record -> visitedElements.add(record.getIdentity().getId())));
        return results.values().stream().flatMap(Function.identity());
    }

    protected CommitIndex getCommitIndex(Commit commit, EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CommitIndexImpl> query = builder.createQuery(CommitIndexImpl.class);
        Root<CommitIndexImpl> root = query.from(CommitIndexImpl.class);
        query.select(root).where(builder.equal(root.get(CommitIndexImpl_.id), commit.getId()));
        CommitIndex commitIndex = null;
        try {
            commitIndex = em.createQuery(query).getSingleResult();
        } catch (NoResultException ignored) {
        }
        if (commitIndex != null) {
            return commitIndex;
        }

        commitIndex = new CommitIndexImpl();
        commitIndex.setCommit(commit);
        commitIndex.setWorkingElementVersions(streamWorkingElementVersions(commit).collect(Collectors.toSet()));
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(commitIndex);
        transaction.commit();
        return commitIndex;
    }

    private Expression<Boolean> getTypeExpression(CriteriaBuilder builder, Root<?> root) {
        return builder.or(metamodelProvider.getAllImplementationClasses().stream()
                .filter(Element.class::isAssignableFrom)
                .map(c -> builder.equal(root.type(), c))
                .toArray(javax.persistence.criteria.Predicate[]::new));
    }
}
