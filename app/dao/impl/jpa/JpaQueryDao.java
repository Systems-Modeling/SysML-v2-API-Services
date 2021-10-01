/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020  InterCAX LLC
 * Copyright (C) 2020  California Institute of Technology ("Caltech")
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

import dao.QueryDao;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Branch;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.impl.ElementVersionImpl;
import org.omg.sysml.query.Query;
import org.omg.sysml.query.impl.QueryImpl;
import org.omg.sysml.query.impl.QueryImpl_;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

public class JpaQueryDao extends SimpleJpaDao<Query, QueryImpl> implements QueryDao {

    @Inject
    public JpaQueryDao(JPAManager jpaManager) {
        super(jpaManager, QueryImpl.class, QueryImpl_.id);
    }

    @Override
    public Optional<Query> persist(Query query) {
        return jpaManager.transact(em -> {
            query.setScope(
                    query.getScope().stream()
                            .filter(identity -> Objects.nonNull(identity.getId()))
                            .map(em::merge)
                            .collect(Collectors.toSet())
            );
            return super.persist(query, em);
        });
    }

    @Override
    public List<Query> findAllByProject(Project project, @Nullable UUID after, @Nullable UUID before, int maxResults) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<QueryImpl> query = builder.createQuery(QueryImpl.class);
            Root<QueryImpl> root = query.from(QueryImpl.class);
            Path<UUID> idPath = root.get(QueryImpl_.id);
            Expression<Boolean> where = builder.equal(root.get(QueryImpl_.owningProject), project);
            query.select(root);
            Paginated<TypedQuery<QueryImpl>> paginated = paginateQuery(after, before, maxResults, query, builder, em, idPath, where);
            List<Query> result = paginated.get()
                    .getResultStream()
                    .collect(Collectors.toList());
            if (paginated.isReversed()) {
                Collections.reverse(result);
            }
            return result;
        });
    }

    @Override
    public Optional<Query> findByProjectAndId(Project project, UUID id) {
        return jpaManager.transact(em -> {
            return _findByProjectAndId(project, id, em);
        });
    }

    private Optional<Query> _findByProjectAndId(Project project, UUID id, EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<QueryImpl> query = builder.createQuery(QueryImpl.class);
        Root<QueryImpl> root = query.from(QueryImpl.class);
        query.select(root)
                .where(builder.and(
                        builder.equal(root.get(QueryImpl_.owningProject), project),
                        builder.equal(root.get(QueryImpl_.id), id)
                ));
        try {
            return Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Query> deleteByProjectAndId(Project project, UUID id) {
        return jpaManager.transact(em -> {
            Optional<Query> query = _findByProjectAndId(project, id, em);
            query.ifPresent(q -> {
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                em.remove(q);
                transaction.commit();
            });
            return query;
        });
    }
}
