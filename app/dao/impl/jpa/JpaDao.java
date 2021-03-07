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

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

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

    protected static class Paginated<X> {
        private final X x;
        private final boolean reversed;

        private Paginated(X x, boolean reversed) {
            this.x = x;
            this.reversed = reversed;
        }

        public X get() {
            return x;
        }

        public boolean isReversed() {
            return reversed;
        }
    }

    protected <X> Paginated<TypedQuery<X>> paginateQuery(@Nullable UUID after, @Nullable UUID before, int maxResults, CriteriaQuery<X> query, CriteriaBuilder builder, EntityManager em, Path<UUID> idPath) {
        return paginateQuery(after, before, maxResults, query, builder, em, idPath, null);
    }

    protected <X> Paginated<TypedQuery<X>> paginateQuery(@Nullable UUID after, @Nullable UUID before, int maxResults, CriteriaQuery<X> query, CriteriaBuilder builder, EntityManager em, Path<UUID> idPath, @Nullable Expression<Boolean> where) {
        if (after != null) {
            Expression<Boolean> expr = builder.greaterThan(idPath, after);
            where = where != null ? builder.and(where, expr) : expr;
        }
        if (before != null) {
            Expression<Boolean> expr = builder.lessThan(idPath, before);
            where = where != null ? builder.and(where, expr) : expr;
        }
        if (where != null) {
            query.where(where);
        }
        boolean reversed = after == null && before != null;
        Function<Path<UUID>, Order> orderFunction = reversed ? builder::desc : builder::asc;
        query.orderBy((orderFunction).apply(idPath));
        TypedQuery<X> typedQuery = em.createQuery(query);
        if (maxResults >= 0) {
            typedQuery.setMaxResults(maxResults);
        }
        return new Paginated<>(typedQuery, reversed);
    }

    protected <X> Paginated<Stream<X>> paginateStream(@Nullable UUID after, @Nullable UUID before, int maxResults, Stream<X> stream, Function<X, UUID> idFunction) {
        if (after != null) {
            stream = stream.filter(x -> idFunction.apply(x).compareTo(after) > 0);
        }
        if (before != null) {
            stream = stream.filter(x -> idFunction.apply(x).compareTo(before) < 0);
        }
        boolean reversed = after == null && before != null;
        Comparator<X> sort = Comparator.comparing(idFunction);
        sort = reversed ? sort.reversed() : sort;
        stream = stream.sorted(sort);
        if (maxResults >= 0) {
            stream = stream.limit(maxResults);
        }
        return new Paginated<>(stream, reversed);
    }
}
