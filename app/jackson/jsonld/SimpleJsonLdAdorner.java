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
import play.Environment;
import play.libs.Json;
import play.mvc.Http.Request;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

public abstract class SimpleJsonLdAdorner<N, P> implements JsonLdAdorner<N, P> {

    protected static final String BASE_FIELD = "@base";
    protected static final String CONTEXT_FIELD = "@context";
    protected static final String IMPORT_FIELD = "@import";

    protected final Environment environment;
    protected final boolean inline;

    private final AtomicReference<ObjectNode> contextCache = new AtomicReference<>();

    public SimpleJsonLdAdorner(Environment environment, boolean inline) {
        this.environment = environment;
        this.inline = inline;
    }

    protected static URI getHost(Request request) {
        return URI.create(String.format("http://%s", request.host()));
    }

    protected static UnaryOperator<JsonNode> constructBasePostProcessor(String field, String basePath, Request request) {
        return json -> {
            JsonNode node = json.path(field);
            if (node.isNull()) {
                return json;
            }
            if (node.isMissingNode()) {
                throw new IllegalStateException(String.format("%s absent", field));
            }
            if (!node.isObject()) {
                throw new IllegalStateException(String.format("%s malformed", field));
            }
            ObjectNode context = Json.mapper().createObjectNode().put(SimpleJsonLdAdorner.BASE_FIELD, getHost(request).resolve("/" + basePath).toString());
            ((ObjectNode) node).set(CONTEXT_FIELD, context);
            return json;
        };
    }

    protected abstract String getType(P parameters);

    protected abstract String getContextPath(P parameters);

    protected abstract String getBasePath(P parameters);

    protected UnaryOperator<JsonNode> getPostProcessor(N entity, Request request, P parameters) {
        return UnaryOperator.identity();
    }

    @Override
    public JsonLdNode<N> adorn(N entity, Request request, P parameters) {
        ObjectNode contextObjectNode = Json.mapper().createObjectNode();
        URI host = getHost(request);

        String contextPath = getContextPath(parameters);
        if (contextPath != null) {
            if (inline) {
                ObjectNode context = contextCache.get();
                if (context == null) {
                    JsonNode json = Json.parse(environment.resourceAsStream("public/" + contextPath));
                    if (!json.isObject()) {
                        throw new IllegalStateException("malformed context file");
                    }
                    context = (ObjectNode) json;
                }
                if (!contextCache.compareAndSet(null, context)) {
                    context = contextCache.get();
                }
                contextObjectNode.setAll((ObjectNode) context.get(CONTEXT_FIELD));
            }
            else {
                contextObjectNode.put(IMPORT_FIELD, host.resolve("/" + contextPath).toString());
            }
        }

        String basePath = getBasePath(parameters);
        if (basePath != null) {
            contextObjectNode.put(BASE_FIELD, host.resolve("/" + basePath).toString());
        }

        return new JsonLdNode<>(contextObjectNode, entity, getType(parameters), getPostProcessor(entity, request, parameters));
    }
}
