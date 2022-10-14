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

package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jackson.jsonld.RecordAdorners.ProjectContainmentParameters;
import org.omg.sysml.lifecycle.Branch;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
import services.SchemaService;

import javax.inject.Inject;
import java.util.*;

public class SchemaController extends BaseController {

    private final SchemaService schemaService;

    @Inject
    public SchemaController(SchemaService schemaService) {
        this.schemaService = schemaService;
    }

    public Result getSchemas(Request request) {
        PageRequest<String> pageRequest = stringRequest(request);
        List<JsonNode> schemas = schemaService.get(
                pageRequest.getAfter(),
                pageRequest.getBefore(),
                pageRequest.getSize()
        );
        return stringResponse(
                !schemas.isEmpty() ? Results.ok(format(schemas)) : Results.notFound(),
                schemas.size(),
                idx -> schemas.get(idx).path("$id").asText(null),
                request,
                pageRequest
        );
    }

    public Result getSchemaById(String schemaId) {
        Optional<JsonNode> schema = schemaService.getById(schemaId);
        if (schema.isEmpty()) {
            return Results.notFound();
        }
        return Results.ok(schema.get());
    }

    private JsonNode format(List<JsonNode> schemas) {
        ObjectNode object = Json.mapper().createObjectNode();
        String schemaValue = schemas.get(0).path("$schema").asText(null);
        if (schemaValue != null) {
            object.put("$schema", schemaValue);
        }
        ObjectNode defs = object.putObject("$defs");
        for (JsonNode schema : schemas) {
            String id = Objects.requireNonNull(schema.path("$id").asText(null));
            ObjectNode schemaNode = defs.putObject(id);
            Iterator<Map.Entry<String, JsonNode>> fieldIterator = schema.fields();
            while (fieldIterator.hasNext()) {
                Map.Entry<String, JsonNode> field = fieldIterator.next();
                String fieldName = field.getKey();
                switch (fieldName) {
                    case "$schema":
                    case "$defs":
                        break;
                    default:
                        schemaNode.put(fieldName, field.getValue());
                }
            }
        }
        return object;
    }
}
