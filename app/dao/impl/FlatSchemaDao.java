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

package dao.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Streams;
import config.MetamodelProvider;
import dao.SchemaDao;
import org.omg.sysml.data.ExternalData;
import org.omg.sysml.data.ExternalRelationship;
import org.omg.sysml.data.ProjectUsage;
import play.libs.Json;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class FlatSchemaDao implements SchemaDao {

    private final TreeMap<String, JsonNode> map;

    @Inject
    public FlatSchemaDao(MetamodelProvider metamodelProvider) {
        try (Stream<Class<?>> interfaces = metamodelProvider.getAllInterfaces().stream()) {
            map = Streams.concat(
                            interfaces
                                    .map(type -> FlatSchemaDao.class.getResourceAsStream(String.format("/json/schema/metamodel/%s.json",
                                            type.getSimpleName())))
                                    .filter(Objects::nonNull),
                            Stream.of(ExternalData.class, ExternalRelationship.class, ProjectUsage.class)
                                    .map(type -> FlatSchemaDao.class.getResourceAsStream(String.format("/json/schema/api/%s.json",
                                            type.getSimpleName())))
                    )
                    .map(input -> {
                        try {
                            return Json.mapper().reader().readTree(input);
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    })
                    .collect(Collectors.toMap(
                            schema -> Objects.requireNonNull(schema.path("$id").asText(null)), Function.identity(),
                            (a, b) -> {
                        throw new IllegalStateException();
                    }, TreeMap::new));
        }
    }

    @Override
    public Optional<JsonNode> findById(String id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public List<JsonNode> findAll(@Nullable String after, @Nullable String before, int maxResults) {
        if (maxResults == 0) {
            return Collections.emptyList();
        }

        Iterator<Map.Entry<String, JsonNode>> schemaIterator = map.entrySet().iterator();
        Map.Entry<String, JsonNode> entry = null;
        if (after != null) {
            while (schemaIterator.hasNext()) {
                entry = schemaIterator.next();
                String id = entry.getKey();
                if (id.compareTo(after) > 0) {
                    break;
                }
            }
        }

        entry = entry == null && schemaIterator.hasNext() ? schemaIterator.next() : entry;
        if (entry == null) {
            return Collections.emptyList();
        }

        Map<String, JsonNode> schemas = new LinkedHashMap<>(maxResults);
        do {
            if (schemas.size() == maxResults) {
                break;
            }

            String id = entry.getKey();
            JsonNode schema = entry.getValue();

            if (before != null) {
                if (id.compareTo(before) >= 0) {
                    break;
                }
            }

            schemas.put(id, schema);

            if (!schemaIterator.hasNext()) {
                break;
            }
            entry = schemaIterator.next();
        } while (true);

        return new ArrayList<>(schemas.values());
    }
}
