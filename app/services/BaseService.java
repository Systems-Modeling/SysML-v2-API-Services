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

package services;

import dao.Dao;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BaseService<I, D extends Dao<I>> {

    protected final D dao;

    protected BaseService(D dao) {
        this.dao = dao;
    }

    public List<I> getAll() {
        return dao.findAll();
    }

    public List<I> getAll(@Nullable UUID after, @Nullable UUID before, int maxResults) {
        return dao.findAll(after, before, maxResults);
    }

    public Optional<I> getById(UUID id) {
        return dao.findById(id);
    }

    public Optional<I> persist(I i) {
        return dao.persist(i);
    }

    public Optional<I> update(I i) {
        return dao.update(i);
    }

    public Optional<I> deleteById(UUID id) {
        return dao.deleteById(id);
    }
}
