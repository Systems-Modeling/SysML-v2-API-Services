/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2021-2023 Twingineer LLC
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

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Streams;
import config.MetamodelProvider;
import dao.DataDao;
import javabean.JavaBeanHelper;
import jpa.manager.JPAManager;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.omg.sysml.data.ProjectUsage;
import org.omg.sysml.internal.CommitDataVersionIndex;
import org.omg.sysml.internal.WorkingDataVersion;
import org.omg.sysml.internal.impl.CommitDataVersionIndexImpl;
import org.omg.sysml.internal.impl.CommitDataVersionIndexImpl_;
import org.omg.sysml.internal.impl.WorkingDataVersionImpl;
import org.omg.sysml.internal.impl.WorkingDataVersionImpl_;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Data;
import org.omg.sysml.lifecycle.DataVersion;
import org.omg.sysml.lifecycle.impl.*;
import org.omg.sysml.query.*;
import org.omg.sysml.query.impl.QueryImpl;
import play.libs.Json;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dao.impl.jpa.JpaDao.paginateQuery;

public class JpaDataDao implements DataDao {

    // TODO Explore alternative to serializing lazy entity attributes that doesn't involve resolving all proxies one level.
    protected static <D extends Data> D resolve(D data, Class<D> clazz) {
        if (data == null) {
            return null;
        }
        D resolved = Hibernate.unproxy(data, clazz);
        JavaBeanHelper.getBeanPropertyValues(resolved).values().stream()
                .flatMap(o -> o instanceof Collection ? ((Collection<?>) o).stream() :
                        Stream.of(o)).filter(o -> o instanceof Data)
                .map(o -> (Data) o).forEach(Hibernate::unproxy);
        return resolved;
    }

    protected static List<Class<?>> SUPPORTED_PRIMITIVE_CONSTRAINT_CLASSES = Arrays.asList(
            Number.class,
            Boolean.class,
            String.class,
            UUID.class
    );

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected static Comparator<Object> UNSAFE_COMPARABLE_COMPARATOR = (o1, o2) -> ((Comparable) o1).compareTo(o2);

    private final JPAManager jpaManager;
    private final MetamodelProvider metamodelProvider;

    @Inject
    public JpaDataDao(JPAManager jpaManager, MetamodelProvider metamodelProvider) {
        this.jpaManager = jpaManager;
        this.metamodelProvider = metamodelProvider;
    }

    @Override
    public List<Data> findByCommitAndQuery(Commit commit, Query query) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(CommitImpl.class, commit.getId());
            Query q = query.getId() == null || em.contains(query) ? query : em.find(QueryImpl.class, query.getId());
            return getCommitIndex(c, em).getWorkingDataVersion().stream()
//                    .filter(scope(q))
                    .map(WorkingDataVersion::getDataVersion)
                    .map(DataVersion::getPayload)
                    .filter(constrain(q.getWhere()))
                    .map(data -> JpaDataDao.resolve(data, Data.class))
                    .collect(Collectors.toList());
        });
    }

    List<DataVersion> findChangesByCommit(Commit commit, UUID after, UUID before, int maxResults, boolean excludeUsed, EntityManager em) {
        CommitDataVersionIndex commitIndex = getCommitIndex(commit, em);

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<DataVersionImpl> query = builder.createQuery(DataVersionImpl.class);
        Root<CommitDataVersionIndexImpl> commitIndexRoot = query.from(CommitDataVersionIndexImpl.class);
        SetJoin<CommitDataVersionIndexImpl, WorkingDataVersionImpl> workingDataVersionJoin = commitIndexRoot.join(CommitDataVersionIndexImpl_.workingDataVersion);
        Join<WorkingDataVersionImpl, DataVersionImpl> dataVersionJoin = workingDataVersionJoin.join(WorkingDataVersionImpl_.dataVersion);
        Join<DataVersionImpl, DataIdentityImpl> dataIdentityJoin = dataVersionJoin.join(DataVersionImpl_.identity);
        Path<UUID> idPath = dataIdentityJoin.get(DataIdentityImpl_.id);
        Expression<Boolean> where = builder.equal(commitIndexRoot.get(CommitDataVersionIndexImpl_.id), commitIndex.getId());
        if (excludeUsed) {
            where = builder.and(where, builder.isNull(workingDataVersionJoin.get(WorkingDataVersionImpl_.source)));
        }
        query.select(dataVersionJoin);
        JpaDao.Paginated<TypedQuery<DataVersionImpl>> paginated = paginateQuery(after, before, maxResults, query, builder, em, idPath, where);
        List<DataVersion> result = paginated.get()
                .getResultStream()
                .collect(Collectors.toList());
        if (paginated.isReversed()) {
            Collections.reverse(result);
        }
        return result;
    }

    protected CommitDataVersionIndex getCommitIndex(Commit commit, EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CommitDataVersionIndexImpl> query = builder.createQuery(CommitDataVersionIndexImpl.class);
        Root<CommitDataVersionIndexImpl> root = query.from(CommitDataVersionIndexImpl.class);
        query.select(root).where(builder.equal(root.get(CommitDataVersionIndexImpl_.id), commit.getId()));
        try {
            return em.createQuery(query).getSingleResult();
        } catch (NoResultException ignored) {
        }

        CommitDataVersionIndex index = new CommitDataVersionIndexImpl();
        index.setCommit(commit);
        index.setWorkingDataVersion(streamWorkingDataVersions(commit, em).collect(Collectors.toSet()));

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        index.getWorkingDataVersion().forEach(em::persist);
        em.persist(index);
        transaction.commit();

        return index;
    }

    protected Stream<WorkingDataVersion> streamWorkingDataVersions(Commit commit, EntityManager em) {
        Set<UUID> visitedIds = ConcurrentHashMap.newKeySet();
        Set<ProjectUsage> projectUsages = ConcurrentHashMap.newKeySet();

        Map<Commit, Set<WorkingDataVersion>> _ownedDataVersions = queryCommitTree(commit,
                c -> c.getChange().stream()
                        .filter(record -> record.getIdentity() != null && record.getIdentity().getId() != null)
                        .filter(record -> !visitedIds.contains(record.getIdentity().getId()))
                        .peek(record -> visitedIds.add(record.getIdentity().getId()))
                        .filter(record -> record.getPayload() != null)
                        .peek(record -> {
                            if (record.getPayload() instanceof ProjectUsage) {
                                projectUsages.add(((ProjectUsage) record.getPayload()));
                            }
                        })
                        .map(version -> {
                            WorkingDataVersionImpl workingDataVersion = new WorkingDataVersionImpl();
                            workingDataVersion.setDataVersion(version);
                            return workingDataVersion;
                        })
                        .collect(Collectors.toSet())
        );
        Stream<WorkingDataVersion> ownedDataVersions = _ownedDataVersions.values().stream()
                .flatMap(Set::stream);

        Set<WorkingDataVersion> _usedDataVersions = ConcurrentHashMap.newKeySet();
        for (ProjectUsage projectUsage : projectUsages) {
            Commit usedCommit = projectUsage.getUsedCommit();
            if (usedCommit == null) {
                continue;
            }
            getCommitIndex(usedCommit, em).getWorkingDataVersion().stream()
                    .map(WorkingDataVersion::getDataVersion)
                    .filter(version -> !visitedIds.contains(version.getIdentity().getId()))
                    .peek(version -> visitedIds.add(version.getIdentity().getId()))
                    .map(version -> {
                        WorkingDataVersionImpl workingDataVersion = new WorkingDataVersionImpl();
                        workingDataVersion.setSource(projectUsage);
                        workingDataVersion.setDataVersion(version);
                        return workingDataVersion;
                    })
                    .forEach(_usedDataVersions::add);
        }
        Stream<WorkingDataVersion> usedDataVersions = _usedDataVersions.stream();

        return Streams.concat(ownedDataVersions, usedDataVersions);
    }

    protected <T> Map<Commit, T> queryCommitTree(Commit commit, Function<Commit, T> query) {
        return queryCommitTree(commit, query, t -> false);
    }

    protected <T> Map<Commit, T> queryCommitTree(Commit commit, Function<Commit, T> query, Predicate<T> terminationCondition) {
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

                JsonNode constrainedValueJson;
                try {
                    constrainedValueJson = primitiveConstraint.getValue() != null ?
                            Json.mapper().readTree(primitiveConstraint.getValue()) :
                            null;
                } catch (IOException e) {
                    throw new IllegalArgumentException(e);
                }

                switch (primitiveConstraint.getProperty()) {
                    case "@id":
                        actualValue = data.getId();
                        constrainedValue = JavaBeanHelper.convert(
                                constrainedValueJson != null ? constrainedValueJson.asText() : null,
                                UUID.class
                        );
                        break;
                    case "@type":
                        try {
                            Class<?> clazz = data instanceof HibernateProxy ?
                                    ((HibernateProxy) data).getHibernateLazyInitializer().getPersistentClass() :
                                    data.getClass();
                            actualValue = metamodelProvider.getInterface(clazz).getSimpleName();
                        } catch (ClassNotFoundException e) {
                            throw new IllegalStateException(e);
                        }
                        constrainedValue = constrainedValueJson != null ? constrainedValueJson.asText() : null;
                        break;
                    default:
                        PropertyDescriptor property = JavaBeanHelper.getBeanProperties(data).get(primitiveConstraint.getProperty());
                        if (property == null) {
                            return false;
                        }
                        if (SUPPORTED_PRIMITIVE_CONSTRAINT_CLASSES.stream()
                                .anyMatch(supported -> supported.isAssignableFrom(property.getPropertyType()))) {
                            actualValue = JavaBeanHelper.getBeanPropertyValue(data, property);
                            constrainedValue = JavaBeanHelper.convert(
                                    constrainedValueJson != null ? constrainedValueJson.asText() : null,
                                    property.getPropertyType()
                            );
                        } else if (Data.class.isAssignableFrom(property.getPropertyType())) {
                            Object _actualValue = JavaBeanHelper.getBeanPropertyValue(data, property);
                            actualValue = _actualValue != null ? ((Data) _actualValue).getId() : null;
                            constrainedValue = constrainedValueJson != null ?
                                    JavaBeanHelper.convert(
                                            // intentionally `textValue` instead of `asText` to get a null value for
                                            // improved error reporting
                                            constrainedValueJson.path("@id").textValue(),
                                            UUID.class
                                    ) : null;
                            if (constrainedValue == null) {
                                throw new IllegalArgumentException();
                            }
                        } else {
                            return false;
                        }
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
/*
    protected Predicate<WorkingDataVersion> scope(Query query) {
        if (query.getScope() == null || query.getScope().isEmpty()) {
            return ev -> true;
        }
        return working -> working.getDataVersion().getIdentity() != null && query.getScope().stream()
                .map(DataIdentity::getId)
                .anyMatch(id -> Objects.equals(id, working.getDataVersion().getIdentity().getId()));
    }
*/
}
