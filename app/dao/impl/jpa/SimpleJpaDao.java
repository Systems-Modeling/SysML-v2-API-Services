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

import jpa.manager.JPAManager;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SimpleJpaDao<I, C extends I> extends JpaDao<I> {

    protected final Class<C> clazz;
    protected final SingularAttribute<? super C, UUID> idAttribute;
    protected final JPAManager jpaManager;

    protected SimpleJpaDao(JPAManager jpaManager, Class<C> clazz, SingularAttribute<? super C, UUID> idAttribute) {
        super(jpaManager);
        this.clazz = clazz;
        this.idAttribute = idAttribute;
        this.jpaManager = jpaManager;
    }

    @Override
    public Optional<I> findById(UUID id) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            @SuppressWarnings("unchecked")
            CriteriaQuery<I> query = (CriteriaQuery<I>) builder.createQuery(clazz);
            Root<C> root = query.from(clazz);
            query.select(root)
                    .where(builder.equal(root.get(idAttribute), id));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<I> findAll() {
        return jpaManager.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            @SuppressWarnings("unchecked")
            CriteriaQuery<I> query = (CriteriaQuery<I>) cb.createQuery(clazz);
            Root<C> root = query.from(clazz);
            query.select(root);
            return em.createQuery(query).getResultList();
        });
    }

    @Override
    public void deleteAll() {
        jpaManager.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            @SuppressWarnings("unchecked")
            CriteriaDelete<I> query = (CriteriaDelete<I>) cb.createCriteriaDelete(clazz);
            return em.createQuery(query).getResultList();
        });
    }
}
