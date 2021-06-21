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

package jackson.jsonld;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import config.MetamodelProvider;
import org.omg.sysml.metamodel.MofObject;
import play.Environment;
import play.libs.Json;
import play.mvc.Http.Request;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class MofObjectJsonLdAdorner<M extends MofObject> implements JsonLdAdorner<M, MofObjectJsonLdAdorner.Parameters> {
    private static final Map<String, ObjectNode> CONTEXT_CACHE = new HashMap<>();

    private final MetamodelProvider metamodelProvider;
    private final Environment environment;
    private final boolean inline;

    public MofObjectJsonLdAdorner(MetamodelProvider metamodelProvider, Environment environment, boolean inline) {
        this.metamodelProvider = metamodelProvider;
        this.environment = environment;
        this.inline = inline;
    }

    private static String getContextPath(String type) {
        return String.format("jsonld/metamodel/%s.jsonld", type);
    }

    @Override
    public JsonLdNode<M> adorn(M entity, Request request, Parameters parameters) {
        URI host = getHost(request);
        String type;
        try {
            type = metamodelProvider.getInterface(entity.getClass()).getSimpleName();
        } catch (ClassNotFoundException | NullPointerException e) {
            throw new IllegalStateException(e);
        }
        ObjectNode contextObjectNode = Json.mapper().createObjectNode();
        contextObjectNode.put("@base",
                host.resolve(
                        String.format("/projects/%s/commits/%s/elements/",
                                parameters.getProjectId(),
                                parameters.getCommitId())
                ).toString()
        );
        if (inline) {
            JsonNode cachedContext = CONTEXT_CACHE.computeIfAbsent(type, t -> {
                JsonNode json = Json.parse(environment.resourceAsStream("public/" + getContextPath(type)));
                if (!(json instanceof ObjectNode)) {
                    throw new IllegalStateException("malformed context file");
                }
                return (ObjectNode) json;
            });
            contextObjectNode.setAll((ObjectNode) cachedContext.get("@context"));
        }
        else {
            contextObjectNode.put("@import", host.resolve("/" + getContextPath(type)).toString());
        }
        return new JsonLdNode<>(contextObjectNode, entity, type);
    }

    public static class Parameters {
        private final UUID projectId;
        private final UUID commitId;

        public Parameters(UUID projectId, UUID commitId) {
            this.projectId = projectId;
            this.commitId = commitId;
        }

        public UUID getProjectId() {
            return projectId;
        }

        public UUID getCommitId() {
            return commitId;
        }
    }
}
