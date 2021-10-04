/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020 InterCAX LLC
 * Copyright (C) 2020 California Institute of Technology ("Caltech")
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
import dao.ElementDao;
import javabean.JavaBeanHelper;
import jpa.manager.JPAManager;
import org.hibernate.Hibernate;
import org.omg.sysml.internal.CommitIndex;
import org.omg.sysml.internal.impl.CommitIndexImpl;
import org.omg.sysml.internal.impl.CommitIndexImpl_;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.DataVersion;
import org.omg.sysml.lifecycle.impl.*;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.impl.SysMLTypeImpl;
import org.omg.sysml.metamodel.impl.SysMLTypeImpl_;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;
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
            CriteriaQuery<SysMLTypeImpl> query = builder.createQuery(SysMLTypeImpl.class);
            Root<SysMLTypeImpl> root = query.from(SysMLTypeImpl.class);
            query.select(root).where(builder.and(
                    builder.equal(root.get(SysMLTypeImpl_.identifier), id),
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
            CriteriaQuery<SysMLTypeImpl> query = builder.createQuery(SysMLTypeImpl.class);
            Root<SysMLTypeImpl> root = query.from(SysMLTypeImpl.class);
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
            CriteriaQuery<SysMLTypeImpl> query = builder.createQuery(SysMLTypeImpl.class);
            Root<SysMLTypeImpl> root = query.from(SysMLTypeImpl.class);
            query.select(root);
            Expression<Boolean> where = getTypeExpression(builder, root);
            Paginated<TypedQuery<SysMLTypeImpl>> paginated = paginateQuery(after, before, maxResults, query, builder, em, root.get(SysMLTypeImpl_.identifier), where);
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
            CriteriaDelete<SysMLTypeImpl> query = builder.createCriteriaDelete(SysMLTypeImpl.class);
            Root<SysMLTypeImpl> root = query.from(SysMLTypeImpl.class);
            query.where(getTypeExpression(builder, root));
            return ((Stream<?>) em.createQuery(query).getResultStream())
                    .map(o -> (Element) o)
                    .collect(Collectors.toList());
        });
    }

    public List<Element> findAllByCommit(Commit commit, @Nullable UUID after, @Nullable UUID before, int maxResults) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(CommitImpl.class, commit.getId());
            CommitIndex commitIndex = dataDao.getCommitIndex(c, em);

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<DataVersionImpl> query = builder.createQuery(DataVersionImpl.class);
            Root<CommitIndexImpl> commitIndexRoot = query.from(CommitIndexImpl.class);
            SetJoin<CommitIndexImpl, DataVersionImpl> workingDataVersionsJoin = commitIndexRoot.join(CommitIndexImpl_.workingDataVersions);
            Join<DataVersionImpl, DataIdentityImpl> dataIdentityJoin = workingDataVersionsJoin.join(DataVersionImpl_.identity);
            Path<UUID> idPath = dataIdentityJoin.get(DataIdentityImpl_.id);
            Expression<Boolean> where = builder.equal(commitIndexRoot.get(CommitIndexImpl_.id), commitIndex.getId());
            query.select(workingDataVersionsJoin);
            Paginated<TypedQuery<DataVersionImpl>> paginated = paginateQuery(after, before, maxResults, query, builder, em, idPath, where);
            List<Element> result = paginated.get()
                    .getResultStream()
                    .map(DataVersion::getPayload)
                    .filter(data -> data instanceof Element)
                    .map(data -> (Element) data)
                    .map(PROXY_RESOLVER)
                    .collect(Collectors.toList());
            if (paginated.isReversed()) {
                Collections.reverse(result);
            }
            return result;
        });
    }

    @Override
    public Optional<Element> findByCommitAndId(Commit commit, UUID id) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(CommitImpl.class, commit.getId());
            CommitIndex commitIndex = dataDao.getCommitIndex(c, em);

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<DataVersionImpl> query = builder.createQuery(DataVersionImpl.class);
            Root<CommitIndexImpl> commitIndexRoot = query.from(CommitIndexImpl.class);
            SetJoin<CommitIndexImpl, DataVersionImpl> workingDataVersionsJoin = commitIndexRoot.join(CommitIndexImpl_.workingDataVersions);
            Join<DataVersionImpl, DataIdentityImpl> dataIdentityJoin = workingDataVersionsJoin.join(DataVersionImpl_.identity);
            query.select(workingDataVersionsJoin).where(
                    builder.equal(commitIndexRoot.get(CommitIndexImpl_.id), commitIndex.getId()),
                    builder.equal(dataIdentityJoin.get(DataIdentityImpl_.id), id)
            );
            try {
                return Optional.of(em.createQuery(query).getSingleResult())
                        .map(DataVersion::getPayload)
                        .filter(data -> data instanceof Element)
                        .map(data -> (Element) data)
                        .map(PROXY_RESOLVER);
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Element> findRootsByCommit(Commit commit, @Nullable UUID after, @Nullable UUID before, int maxResults) {
        return jpaManager.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(CommitImpl.class, commit.getId());
            Stream<Element> stream = dataDao.getCommitIndex(c, em).getWorkingDataVersions().stream()
                    .map(DataVersion::getPayload)
                    .filter(data -> (data instanceof Element) && !(data instanceof Relationship))
                    .map(data -> (Element) data)
                    .filter(element -> element.getOwner() == null);
            Paginated<Stream<Element>> paginatedStream = paginateStream(after, before, maxResults, stream, Element::getIdentifier);
            List<Element> result = paginatedStream.get()
                    .map(PROXY_RESOLVER)
                    .collect(Collectors.toList());
            if (paginatedStream.isReversed()) {
                Collections.reverse(result);
            }
            return result;
        });
    }

    private Expression<Boolean> getTypeExpression(CriteriaBuilder builder, Root<?> root) {
        return builder.or(implementationClasses.stream()
                .map(c -> builder.equal(root.type(), c))
                .toArray(javax.persistence.criteria.Predicate[]::new)
        );
    }
}
