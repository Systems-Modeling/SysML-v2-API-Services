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

import dao.TagDao;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.Tag;
import org.omg.sysml.lifecycle.impl.TagImpl;
import org.omg.sysml.lifecycle.impl.TagImpl_;

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
public class JpaTagDao extends SimpleJpaDao<Tag, TagImpl> implements TagDao {

    @Inject
    public JpaTagDao(JPAManager jpaManager) {
        super(jpaManager, TagImpl.class, TagImpl_.id);
    }

    @Override
    public List<Tag> findAllByProject(Project project, UUID after, UUID before, int maxResults) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<TagImpl> query = builder.createQuery(TagImpl.class);
            Root<TagImpl> root = query.from(TagImpl.class);
            query.select(root);
            Expression<Boolean> where = builder.equal(root.get(TagImpl_.owningProject), project);
            Paginated<TypedQuery<TagImpl>> paginated = paginateQuery(after, before, maxResults, query, builder, em, root.get(TagImpl_.id), where);
            List<Tag> result = paginated.get()
                    .getResultStream()
                    .collect(Collectors.toList());
            if (paginated.isReversed()) {
                Collections.reverse(result);
            }
            return result;
        });
    }

    @Override
    public Optional<Tag> findByProjectAndId(Project project, UUID id) {
        return jpaManager.transact(em -> {
            return _findByProjectAndId(project, id, em);
        });
    }

    protected Optional<Tag> _findByProjectAndId(Project project, UUID id, EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TagImpl> query = builder.createQuery(TagImpl.class);
        Root<TagImpl> root = query.from(TagImpl.class);
        query.select(root)
                .where(builder.and(
                        builder.equal(root.get(TagImpl_.owningProject), project),
                        builder.equal(root.get(TagImpl_.id), id)
                ));
        Optional<Tag> tag;
        try {
            tag = Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
        return tag;
    }

    @Override
    public Optional<Tag> deleteByProjectAndId(Project project, UUID id) {
        return jpaManager.transact(em -> {
            Optional<Tag> tag = _findByProjectAndId(project, id, em);
            tag.ifPresent(b -> {
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                em.remove(b);
                transaction.commit();
            });
            return tag;
        });
    }
}
