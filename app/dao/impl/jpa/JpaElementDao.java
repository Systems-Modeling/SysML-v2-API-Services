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
import org.omg.sysml.lifecycle.ElementVersion;
import org.omg.sysml.lifecycle.impl.ElementIdentityImpl;
import org.omg.sysml.lifecycle.impl.ElementIdentityImpl_;
import org.omg.sysml.lifecycle.impl.ElementVersionImpl;
import org.omg.sysml.lifecycle.impl.ElementVersionImpl_;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.impl.MofObjectImpl;
import org.omg.sysml.metamodel.impl.MofObjectImpl_;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class JpaElementDao extends JpaDao<Element> implements ElementDao {
    // TODO Explore alternative to serializing lazy entity attributes that doesn't involve resolving all proxies one level.
    static Consumer<Element> PROXY_RESOLVER = element -> JavaBeanHelper.getBeanPropertyValues(element).values().stream().flatMap(o -> o instanceof Collection ? ((Collection<?>) o).stream() : Stream.of(o)).filter(o -> o instanceof Element).map(o -> (Element) o).forEach(Hibernate::unproxy);

    @Inject
    private MetamodelProvider metamodelProvider;

    @Inject
    private JPAManager jpa;

    @Override
    protected JPAManager getJpaManager() {
        return jpa;
    }

    @Override
    public Optional<Element> findById(UUID id) {
        return jpa.transact(em -> {
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
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<MofObjectImpl> query = builder.createQuery(MofObjectImpl.class);
            Root<MofObjectImpl> root = query.from(MofObjectImpl.class);
            query.select(root).where(getTypeExpression(builder, root));
            return em.createQuery(query).getResultStream().map(o -> (Element) o).collect(Collectors.toList());
        });
    }

    @Override
    public void deleteAll() {
        jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaDelete<MofObjectImpl> query = builder.createCriteriaDelete(MofObjectImpl.class);
            Root<MofObjectImpl> root = query.from(MofObjectImpl.class);
            query.where(getTypeExpression(builder, root));
            return ((Stream<?>) em.createQuery(query).getResultStream()).map(o -> (Element) o).collect(Collectors.toList());
        });
    }

    @Override
    public Set<Element> findAllByCommit(Commit commit) {
        return jpa.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(metamodelProvider.getImplementationClass(Commit.class), commit.getId());
            return getCommitIndex(c, em).getWorkingElementVersions().stream()
                    .map(ElementVersion::getData)
                    .filter(mof -> mof instanceof Element)
                    .map(mof -> (Element) mof)
                    .peek(PROXY_RESOLVER)
                    .collect(Collectors.toSet());
        });
    }

    @Override
    public Optional<Element> findByCommitAndId(Commit commit, UUID id) {
        return jpa.transact(em -> {
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
                        .map(ElementVersion::getData).filter(mof -> mof instanceof Element)
                        .map(mof -> (Element) mof).map(element -> {
                    PROXY_RESOLVER.accept(element);
                    return element;
                });
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public Set<Element> findRootsByCommit(Commit commit) {
        return jpa.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(metamodelProvider.getImplementationClass(Commit.class), commit.getId());
            return getCommitIndex(c, em).getWorkingElementVersions().stream()
                    .map(ElementVersion::getData)
                    .filter(mof -> mof instanceof Element)
                    .map(mof -> (Element) mof)
                    .filter(element -> element.getOwner() == null)
                    .peek(PROXY_RESOLVER)
                    .collect(Collectors.toSet());
        });
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
                c -> c.getChanges().stream()
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
                .toArray(Predicate[]::new));
    }
}
