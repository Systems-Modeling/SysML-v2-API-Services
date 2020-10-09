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

package jpa.manager.impl;

import jpa.manager.JPAManager;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;
import java.util.function.Function;

@Singleton
public class HibernateManager implements JPAManager {
    public static final String PERSISTENCE_UNIT_NAME = "sysml2-hibernate";

    private final EntityManagerFactory entityManagerFactory;

    public HibernateManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    @Override
    public String getPersistenceUnitName() {
        return PERSISTENCE_UNIT_NAME;
    }

    @Override
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @Override
    public <R> R transact(Function<EntityManager, R> function) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            return function.apply(entityManager);
        } finally {
            entityManager.close();
        }

    }

    @Override
    public void transact(Consumer<EntityManager> consumer) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            consumer.accept(entityManager);
        } finally {
            entityManager.close();
        }

    }
}
