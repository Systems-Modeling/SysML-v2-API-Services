/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2021 Twingineer LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 */

package dao.impl.jpa;

import config.MetamodelProvider;
import dao.DataDao;
import javabean.JavaBeanHelper;
import jpa.manager.JPAManager;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.omg.sysml.internal.CommitIndex;
import org.omg.sysml.internal.impl.CommitIndexImpl;
import org.omg.sysml.internal.impl.CommitIndexImpl_;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Data;
import org.omg.sysml.lifecycle.DataIdentity;
import org.omg.sysml.lifecycle.DataVersion;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.query.*;
import org.omg.sysml.query.impl.QueryImpl;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JpaDataDao extends JpaDao<Data> implements DataDao {

    // TODO Explore alternative to serializing lazy entity attributes that doesn't involve resolving all proxies one level.
    protected static UnaryOperator<Data> PROXY_RESOLVER = data -> {
        data = Hibernate.unproxy(data, Data.class);
        JavaBeanHelper.getBeanPropertyValues(data).values().stream()
                .flatMap(o -> o instanceof Collection ? ((Collection<?>) o).stream() :
                        Stream.of(o)).filter(o -> o instanceof Data)
                .map(o -> (Data) o).forEach(Hibernate::unproxy);
        return data;
    };

    protected static List<Class<?>> SUPPORTED_PRIMITIVE_CONSTRAINT_CLASSES = Arrays.asList(
            Number.class,
            Boolean.class,
            String.class
    );

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected static Comparator<Object> UNSAFE_COMPARABLE_COMPARATOR = (o1, o2) -> ((Comparable) o1).compareTo(o2);

    private final MetamodelProvider metamodelProvider;

    @Inject
    public JpaDataDao(JPAManager jpaManager, MetamodelProvider metamodelProvider) {
        super(jpaManager);
        this.metamodelProvider = metamodelProvider;
    }

    @Override
    public Optional<Data> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Data> findAll() {
        return null;
    }

    @Override
    public List<Data> findAll(@Nullable UUID after, @Nullable UUID before, int maxResults) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Data> findByCommitAndQuery(Commit commit, Query query) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(CommitImpl.class, commit.getId());
            Query q = query.getId() == null || em.contains(query) ? query : em.find(QueryImpl.class, query.getId());
            return getCommitIndex(c, em).getWorkingDataVersions().stream()
                    .filter(scope(q))
                    .map(DataVersion::getPayload)
                    .filter(constrain(q.getWhere()))
                    .map(PROXY_RESOLVER)
                    .collect(Collectors.toList());
        });
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
        commitIndex.setWorkingDataVersions(streamWorkingDataVersions(commit).collect(Collectors.toSet()));
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(commitIndex);
        transaction.commit();
        return commitIndex;
    }

    protected Stream<DataVersion> streamWorkingDataVersions(Commit commit) {
        Set<UUID> visitedIds = ConcurrentHashMap.newKeySet();
        Map<Commit, Stream<DataVersion>> results = queryCommitTree(commit,
                c -> c.getChange().stream()
                        .filter(record -> record.getIdentity() != null && record.getIdentity().getId() != null && record.getPayload() != null)
                        .filter(record -> !visitedIds.contains(record.getIdentity().getId()))
                        .peek(record -> visitedIds.add(record.getIdentity().getId())));
        return results.values().stream().flatMap(Function.identity());
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

    protected Predicate<Data> constrain(Constraint constraint) {
        if (constraint == null) {
            return ignored -> true;
        }
        else if (constraint instanceof PrimitiveConstraint) {
            PrimitiveConstraint primitiveConstraint = (PrimitiveConstraint) constraint;
            return data -> {
                Object actualValue;
                Object constrainedValue;
                switch (primitiveConstraint.getProperty()) {
                    case "@type":
                        try {
                            Class<?> clazz = data instanceof HibernateProxy ?
                                    ((HibernateProxy) data).getHibernateLazyInitializer().getPersistentClass() :
                                    data.getClass();
                            actualValue = metamodelProvider.getInterface(clazz).getSimpleName();
                        } catch (ClassNotFoundException e) {
                            throw new IllegalStateException(e);
                        }
                        constrainedValue = primitiveConstraint.getValue();
                        break;
                    default:
                        PropertyDescriptor property = JavaBeanHelper.getBeanProperties(data).get(primitiveConstraint.getProperty());
                        if (property == null) {
                            return false;
                        }
                        if (SUPPORTED_PRIMITIVE_CONSTRAINT_CLASSES.stream()
                                .noneMatch(supported -> supported.isAssignableFrom(property.getPropertyType()))) {
                            return false;
                        }
                        actualValue = JavaBeanHelper.getBeanPropertyValue(data, property);
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

    protected Predicate<DataVersion> scope(Query query) {
        if (query.getScope() == null || query.getScope().isEmpty()) {
            return ev -> true;
        }
        return ev -> ev.getIdentity() != null && query.getScope().stream()
                .map(DataIdentity::getId)
                .anyMatch(id -> Objects.equals(id, ev.getIdentity().getId()));
    }
}
