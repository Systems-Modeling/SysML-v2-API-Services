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
import jackson.JacksonHelper;
import jackson.jsonld.JsonLdAdorner;
import jackson.jsonld.JsonLdNode;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static jackson.JsonLdMofObjectAdornment.JSONLD_MIME_TYPE;

public abstract class JsonLdController<E, P> extends BaseController {

    protected static final boolean INLINE_JSON_LD_CONTEXT_DEFAULT = true;
    protected static final boolean INLINE_JSON_LD_CONTEXT = Optional.ofNullable(System.getenv("INLINE_JSON_LD_CONTEXT"))
            .map(Boolean::parseBoolean)
            .orElse(INLINE_JSON_LD_CONTEXT_DEFAULT);

    protected Result buildResult(E element, Request request, P parameters) {
        if (element == null) {
            return Results.notFound();
        }
        boolean ld = respondWithJsonLd(request);
        Object o = ld ?
                getAdorner().adorn(element, request, parameters) :
                element;
        JsonNode json = Json.toJson(o);
        Result result = Results.ok(json);
        if (ld) {
            result = result.as(JSONLD_MIME_TYPE);
        }
        return result;
    }

    protected JsonNode buildJson(Collection<E> elements, @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<? extends E> elementClass, Request request, P parameters, JsonLdFunction jsonLdFunction, boolean jsonLd) {
        Collection<?> collection;
        if (jsonLd) {
            JsonLdAdorner<E, P> adorner = getAdorner();
            collection = elements.stream().map(e -> adorner.adorn(e, request, parameters)).collect(Collectors.toList());
        }
        else {
            collection = elements;
        }
        return jsonLdFunction.apply(
                collection,
                jsonLd ? List.class : collectionClass,
                jsonLd ? JsonLdNode.class : elementClass);
    }

    protected Result buildResult(JsonNode json, boolean jsonLd) {
        Result result = Results.ok(json);
        if (jsonLd) {
            result = result.as(JSONLD_MIME_TYPE);
        }
        return result;
    }

    protected Result buildResult(Collection<E> entities, @SuppressWarnings("rawtypes") Class<? extends Collection> collectionClass, Class<? extends E> elementClass, Request request, P parameters) {
        boolean jsonLd = respondWithJsonLd(request);
        JsonNode json = buildJson(entities, collectionClass, elementClass, request, parameters, JacksonHelper::collectionToTree, jsonLd);
        return buildResult(json, jsonLd);
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
