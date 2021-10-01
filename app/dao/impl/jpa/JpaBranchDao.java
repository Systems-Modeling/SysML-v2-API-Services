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

import dao.BranchDao;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Branch;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.impl.BranchImpl;
import org.omg.sysml.lifecycle.impl.BranchImpl_;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class JpaBranchDao extends SimpleJpaDao<Branch, BranchImpl> implements BranchDao {

    @Inject
    public JpaBranchDao(JPAManager jpaManager) {
        super(jpaManager, BranchImpl.class, BranchImpl_.id);
    }

    @Override
    public List<Branch> findAllByProject(Project project, UUID after, UUID before, int maxResults) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<BranchImpl> query = builder.createQuery(BranchImpl.class);
            Root<BranchImpl> root = query.from(BranchImpl.class);
            query.select(root);
            Expression<Boolean> where = builder.equal(root.get(BranchImpl_.owningProject), project);
            Paginated<TypedQuery<BranchImpl>> paginated = paginateQuery(after, before, maxResults, query, builder, em, root.get(BranchImpl_.id), where);
            List<Branch> result = paginated.get()
                    .getResultStream()
                    .collect(Collectors.toList());
            if (paginated.isReversed()) {
                Collections.reverse(result);
            }
            return result;
        });
    }

    @Override
    public Optional<Branch> findByProjectAndId(Project project, UUID id) {
        return jpaManager.transact(em ->  {
            return _findByProjectAndId(project, id, em);
        });
    }

    protected Optional<Branch> _findByProjectAndId(Project project, UUID id, EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BranchImpl> query = builder.createQuery(BranchImpl.class);
        Root<BranchImpl> root = query.from(BranchImpl.class);
        query.select(root)
                .where(builder.and(
                        builder.equal(root.get(BranchImpl_.owningProject), project),
                        builder.equal(root.get(BranchImpl_.id), id)
                ));
        Optional<Branch> branch;
        try {
            branch = Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
        return branch;
    }

    @Override
    public Optional<Branch> deleteByProjectAndId(Project project, UUID id) {
        return jpaManager.transact(em -> {
            Optional<Branch> branch = _findByProjectAndId(project, id, em);
            branch.ifPresent(b -> {
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                em.remove(b);
                transaction.commit();
            });
            return branch;
        });
    }
}
