/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2022 Twingineer LLC
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

import com.fasterxml.jackson.databind.JsonNode;
import dao.SchemaDao;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class SchemaService {

    private final SchemaDao dao;

    @Inject
    public SchemaService(SchemaDao dao) {
        this.dao = dao;
    }

    public Optional<JsonNode> getById(String id) {
        return dao.findById(id);
    }

    public List<JsonNode> get(String after, String before, int maxResults) {
        return dao.findAll(after, before, maxResults);
    }
}
