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

import dao.Dao;
import jpa.manager.JPAManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

public abstract class JpaDao<E> implements Dao<E> {
    
    protected final JPAManager jpaManager;

    protected JpaDao(JPAManager jpaManager) {
        this.jpaManager = jpaManager;
    }

    @Override
    public Optional<E> update(E e) {
        return Optional.ofNullable(jpaManager.transact(em -> {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(e);
            transaction.commit();
            return e;
        }));
    }

    @Override
    public Optional<E> persist(E e) {
        return jpaManager.transact(em -> {
            return persist(e, em);
        });
    }

    protected Optional<E> persist(E e, EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(e);
        transaction.commit();
        return Optional.ofNullable(e);
    }

    @Override
    public void delete(E e) {
        jpaManager.transact(em -> {
            em.remove(e);
        });
    }
}
