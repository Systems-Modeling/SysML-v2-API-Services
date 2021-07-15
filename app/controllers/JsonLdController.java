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

package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jackson.JacksonHelper;
import jackson.jsonld.JsonLdAdorner;
import jackson.jsonld.JsonLdNode;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;

import java.util.Collection;
import java.util.Optional;
import java.util.function.UnaryOperator;

public abstract class JsonLdController<E, P> extends BaseController {

    private static final String JSONLD_MIME_TYPE = "application/ld+json";

    protected static final boolean INLINE_JSON_LD_CONTEXT_DEFAULT = true;
    protected static final boolean INLINE_JSON_LD_CONTEXT = Optional.ofNullable(System.getenv("INLINE_JSON_LD_CONTEXT"))
            .map(Boolean::parseBoolean)
            .orElse(INLINE_JSON_LD_CONTEXT_DEFAULT);

    protected JsonNode buildJson(E element, Request request, P parameters, boolean ld) {
        return buildJson(element, request, parameters, ld, Json.mapper());
    }

    protected JsonNode buildJson(E element, Request request, P parameters, boolean ld, ObjectMapper mapper) {
        return buildJson(element, request, parameters, ld, mapper, UnaryOperator.identity());
    }

    protected JsonNode buildJson(E element, Request request, P parameters, boolean ld, ObjectMapper mapper, UnaryOperator<ObjectWriter> writerOperator) {
        return buildJson(element, request, parameters, ld, mapper, writerOperator, UnaryOperator.identity());
    }

    protected JsonNode buildJson(E element, Request request, P parameters, boolean ld, ObjectMapper mapper, UnaryOperator<ObjectWriter> writerOperator, UnaryOperator<ObjectReader> readerOperator) {
        Object o = ld ? getAdorner().adorn(element, request, parameters) : element;
        JsonNode json;
        json = JacksonHelper.objectToTree(
                o,
                mapper,
                om -> JacksonHelper.writer(o, om, writerOperator),
                om -> JacksonHelper.reader(o, om, readerOperator)
        );
        if (ld) {
            json = ((JsonLdNode<?>) o).postProcess(json);
        }
        return json;
    }

    protected JsonNode buildJson(Collection<E> elements, @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<? extends E> elementClass, Request request, P parameters, boolean ld) {
        return buildJson(elements, collectionClass, elementClass, request, parameters, ld, Json.mapper());
    }

    protected JsonNode buildJson(Collection<E> elements, @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<? extends E> elementClass, Request request, P parameters, boolean ld, ObjectMapper mapper) {
        return buildJson(elements, collectionClass, elementClass, request, parameters, ld, mapper, UnaryOperator.identity());
    }

    protected JsonNode buildJson(Collection<E> elements, @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<? extends E> elementClass, Request request, P parameters, boolean ld, ObjectMapper mapper, UnaryOperator<ObjectWriter> writerOperator) {
        return buildJson(elements, collectionClass, elementClass, request, parameters, ld, mapper, writerOperator, UnaryOperator.identity());
    }

    protected JsonNode buildJson(Collection<E> elements, @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<? extends E> elementClass, Request request, P parameters, boolean ld, ObjectMapper mapper, UnaryOperator<ObjectWriter> writerOperator, UnaryOperator<ObjectReader> readerOperator) {
        if (ld) {
            ArrayNode arrayNode = mapper.createArrayNode();
            elements.forEach(e -> arrayNode.add(buildJson(e, request, parameters, true, mapper, writerOperator, readerOperator)));
            return arrayNode;
        }
        return JacksonHelper.collectionToTree(elements, collectionClass, elementClass, mapper, writerOperator, readerOperator);
    }

    protected Result buildResult(JsonNode json, boolean ld) {
        Result result = Results.ok(json);
        if (ld) {
            result = result.as(JSONLD_MIME_TYPE);
        }
        return result;
    }

    protected Result buildResult(E element, Request request, P parameters) {
        if (element == null) {
            return Results.notFound();
        }
        boolean ld = respondWithJsonLd(request);
        JsonNode json = buildJson(element, request, parameters, ld);
        return buildResult(json, ld);
    }

    protected Result buildResult(Collection<E> entities, @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<? extends E> elementClass, Request request, P parameters) {
        boolean ld = respondWithJsonLd(request);
        JsonNode json = buildJson(entities, collectionClass, elementClass, request, parameters, ld);
        return buildResult(json, ld);
    }

    protected abstract JsonLdAdorner<E, P> getAdorner();

    protected boolean respondWithJsonLd(Request request) {
        return request.accepts(JSONLD_MIME_TYPE) && !request.accepts(Http.MimeTypes.JSON);
    }

    @FunctionalInterface
    protected interface JsonLdFunction {
        JsonNode apply(Collection<?> elements, @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<?> elementClass);
    }
}
