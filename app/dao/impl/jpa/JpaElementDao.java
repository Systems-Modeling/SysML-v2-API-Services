/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020 InterCAX LLC
 * Copyright (C) 2020 California Institute of Technology ("Caltech")
 * Copyright (C) 2021-2022 Twingineer LLC
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
import dao.ElementDao;
import jpa.manager.JPAManager;
import org.omg.sysml.data.ProjectUsage;
import org.omg.sysml.internal.CommitDataVersionIndex;
import org.omg.sysml.internal.CommitNamedElementIndex;
import org.omg.sysml.internal.WorkingDataVersion;
import org.omg.sysml.internal.impl.*;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.DataVersion;
import org.omg.sysml.lifecycle.impl.*;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.impl.ElementImpl;
import org.omg.sysml.metamodel.impl.ElementImpl_;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class JpaElementDao extends JpaDao<Element> implements ElementDao {

    private final Set<Class<?>> implementationClasses;
    private final JpaDataDao dataDao;

    @Inject
    public JpaElementDao(JPAManager jpaManager, MetamodelProvider metamodelProvider, JpaDataDao dataDao) {
        super(jpaManager);
        this.implementationClasses = metamodelProvider.getAllImplementationClasses().stream()
                .filter(Element.class::isAssignableFrom)
                .collect(Collectors.toSet());
        this.dataDao = dataDao;
    }

    @Override
    public Optional<Element> findById(UUID id) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<ElementImpl> query = builder.createQuery(ElementImpl.class);
            Root<ElementImpl> root = query.from(ElementImpl.class);
            query.select(root).where(builder.and(
                    builder.equal(root.get(ElementImpl_.elementId), id),
                    getTypeExpression(builder, root)
            ));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Element> findAll() {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<ElementImpl> query = builder.createQuery(ElementImpl.class);
            Root<ElementImpl> root = query.from(ElementImpl.class);
            query.select(root).where(getTypeExpression(builder, root));
            return em.createQuery(query).getResultStream()
                    .map(o -> (Element) o)
                    .collect(Collectors.toList());
        });
    }

    @Override
    public List<Element> findAll(@Nullable UUID after, @Nullable UUID before, int maxResults) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<ElementImpl> query = builder.createQuery(ElementImpl.class);
            Root<ElementImpl> root = query.from(ElementImpl.class);
            query.select(root);
            Expression<Boolean> where = getTypeExpression(builder, root);
            Paginated<TypedQuery<ElementImpl>> paginated = paginateQuery(after, before, maxResults, query, builder, em, root.get(ElementImpl_.elementId), where);
            List<Element> result = paginated.get()
                    .getResultStream()
                    .map(o -> (Element) o)
                    .collect(Collectors.toList());
            if (paginated.isReversed()) {
                Collections.reverse(result);
            }
            return result;
        });
    }

    @Override
    public void deleteAll() {
        jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaDelete<ElementImpl> query = builder.createCriteriaDelete(ElementImpl.class);
            Root<ElementImpl> root = query.from(ElementImpl.class);
            query.where(getTypeExpression(builder, root));
            return ((Stream<?>) em.createQuery(query).getResultStream())
                    .map(o -> (Element) o)
                    .collect(Collectors.toList());
        });
    }

    public List<Element> findAllByCommit(Commit commit, boolean excludeUsed, @Nullable UUID after, @Nullable UUID before, int maxResults) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(CommitImpl.class, commit.getId());
            return dataDao.findChangesByCommit(c, after, before, maxResults, excludeUsed, em)
                    .stream()
                    .map(DataVersion::getPayload)
                    .filter(data -> data instanceof Element)
                    .map(data -> (Element) data)
                    .map(element -> JpaDataDao.resolve(element, Element.class))
                    .collect(Collectors.toList());
        });
    }

    @Override
    public Optional<Element> findByCommitAndId(Commit commit, UUID id, boolean excludeUsed) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(CommitImpl.class, commit.getId());
            CommitDataVersionIndex commitIndex = dataDao.getCommitIndex(c, em);

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<DataVersionImpl> query = builder.createQuery(DataVersionImpl.class);
            Root<CommitDataVersionIndexImpl> commitIndexRoot = query.from(CommitDataVersionIndexImpl.class);
            SetJoin<CommitDataVersionIndexImpl, WorkingDataVersionImpl> workingDataVersionJoin = commitIndexRoot.join(CommitDataVersionIndexImpl_.workingDataVersion);
            Join<WorkingDataVersionImpl, DataVersionImpl> dataVersionJoin = workingDataVersionJoin.join(WorkingDataVersionImpl_.dataVersion);
            Join<DataVersionImpl, DataIdentityImpl> dataIdentityJoin = dataVersionJoin.join(DataVersionImpl_.identity);
            Expression<Boolean> where = builder.and(
                    builder.equal(commitIndexRoot.get(CommitDataVersionIndexImpl_.id), commitIndex.getId()),
                    builder.equal(dataIdentityJoin.get(DataIdentityImpl_.id), id)
            );
            if (excludeUsed) {
                where = builder.and(where, builder.isNull(workingDataVersionJoin.get(WorkingDataVersionImpl_.source)));
            }
            query.select(dataVersionJoin).where(where);
            try {
                return Optional.of(em.createQuery(query).getSingleResult())
                        .map(DataVersion::getPayload)
                        .filter(data -> data instanceof Element)
                        .map(data -> (Element) data)
                        .map(element -> JpaDataDao.resolve(element, Element.class));
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Element> findRootsByCommit(Commit commit, boolean excludeUsed, @Nullable UUID after, @Nullable UUID before, int maxResults) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(CommitImpl.class, commit.getId());
            Stream<Element> stream = dataDao.getCommitIndex(c, em).getWorkingDataVersion().stream()
                    .filter(working -> !excludeUsed || working.getSource() == null)
                    .map(WorkingDataVersion::getDataVersion)
                    .map(DataVersion::getPayload)
                    .filter(data -> (data instanceof Element) && !(data instanceof Relationship))
                    .map(data -> (Element) data)
                    .filter(element -> element.getOwner() == null);
            Paginated<Stream<Element>> paginatedStream = paginateStream(after, before, maxResults, stream, Element::getElementId);
            List<Element> result = paginatedStream.get()
                    .map(element -> JpaDataDao.resolve(element, Element.class))
                    .collect(Collectors.toList());
            if (paginatedStream.isReversed()) {
                Collections.reverse(result);
            }
            return result;
        });
    }

    public Optional<Element> findByCommitAndQualifiedName(Commit commit, String qualifiedName) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(CommitImpl.class, commit.getId());
            return Optional.ofNullable(getCommitNamedElementIndex(c, em).getWorkingNamedElement().get(qualifiedName));
        });
    }

    @Override
    public Optional<ProjectUsage> findProjectUsageByCommitAndId(Commit commit, UUID elementId) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(CommitImpl.class, commit.getId());
            CommitDataVersionIndex commitIndex = dataDao.getCommitIndex(c, em);

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<WorkingDataVersionImpl> query = builder.createQuery(WorkingDataVersionImpl.class);
            Root<CommitDataVersionIndexImpl> commitIndexRoot = query.from(CommitDataVersionIndexImpl.class);
            SetJoin<CommitDataVersionIndexImpl, WorkingDataVersionImpl> workingDataVersionJoin = commitIndexRoot.join(CommitDataVersionIndexImpl_.workingDataVersion);
            Join<WorkingDataVersionImpl, DataVersionImpl> dataVersionJoin = workingDataVersionJoin.join(WorkingDataVersionImpl_.dataVersion);
            Join<DataVersionImpl, DataIdentityImpl> dataIdentityJoin = dataVersionJoin.join(DataVersionImpl_.identity);
            Expression<Boolean> where = builder.and(
                    builder.equal(commitIndexRoot.get(CommitDataVersionIndexImpl_.id), commitIndex.getId()),
                    builder.equal(dataIdentityJoin.get(DataIdentityImpl_.id), elementId)
            );
            query.select(workingDataVersionJoin).where(where);
            try {
                return Optional.of(em.createQuery(query).getSingleResult())
                        .map(WorkingDataVersion::getSource)
                        .map(projectUsage -> JpaDataDao.resolve(projectUsage, ProjectUsage.class));
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    protected CommitNamedElementIndex getCommitNamedElementIndex(Commit commit, EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CommitNamedElementIndexImpl> query = builder.createQuery(CommitNamedElementIndexImpl.class);
        Root<CommitNamedElementIndexImpl> root = query.from(CommitNamedElementIndexImpl.class);
        query.select(root).where(builder.equal(root.get(CommitNamedElementIndexImpl_.id), commit.getId()));
        try {
            return em.createQuery(query).getSingleResult();
        } catch (NoResultException ignored) {
        }

        CommitNamedElementIndexImpl index = new CommitNamedElementIndexImpl();
        index.setCommit(commit);
        index.setWorkingNamedElement(
                streamWorkingElements(commit, em)
                        .filter(element -> Objects.nonNull(element.getQualifiedName()))
                        .collect(Collectors.toUnmodifiableMap(Element::getQualifiedName,
                                Function.identity(), (e1, e2) -> e1))
        );
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(index);
        transaction.commit();
        return index;
    }

    protected Stream<Element> streamWorkingElements(Commit commit, EntityManager em) {
        return dataDao.getCommitIndex(commit, em).getWorkingDataVersion().stream()
                .map(WorkingDataVersion::getDataVersion)
                .map(DataVersion::getPayload)
                .filter(data -> data instanceof Element)
                .map(data -> (Element) data);
    }

    private Expression<Boolean> getTypeExpression(CriteriaBuilder builder, Root<?> root) {
        return builder.or(implementationClasses.stream()
                .map(c -> builder.equal(root.type(), c))
                .toArray(javax.persistence.criteria.Predicate[]::new)
        );
    }
}
