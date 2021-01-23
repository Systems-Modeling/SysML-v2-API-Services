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
import com.google.common.primitives.Bytes;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import jackson.JsonLdMofObjectAdornment;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.MofObject;
import play.Environment;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.ElementService;

import javax.inject.Inject;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static jackson.JsonLdMofObjectAdornment.JSONLD_MIME_TYPE;

public class ElementController extends Controller {

    private final ElementService elementService;
    private final MetamodelProvider metamodelProvider;
    private final Environment environment;

    @Inject
    public ElementController(ElementService elementService, MetamodelProvider metamodelProvider, Environment environment) {
        this.elementService = elementService;
        this.metamodelProvider = metamodelProvider;
        this.environment = environment;
    }

    private static final boolean INLINE_JSON_LD_CONTEXT_DEFAULT = true;
    private static final boolean INLINE_JSON_LD_CONTEXT = Optional.ofNullable(System.getenv("INLINE_JSON_LD_CONTEXT"))
            .map(Boolean::parseBoolean).orElse(INLINE_JSON_LD_CONTEXT_DEFAULT);

    public Result byId(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Element> element = elementService.getById(uuid);
        return element.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Element> elements = elementService.getAll();
        return ok(JacksonHelper.collectionToTree(elements, List.class, metamodelProvider.getImplementationClass(Element.class)));
    }

    public Result create(Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        MofObject requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(MofObject.class));
        if (!(requestedObject instanceof Element)) {
            return Results.badRequest();
        }
        Optional<Element> responseElement = elementService.create(((Element) requestedObject));
        return responseElement.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }

    public Result getElementsByProjectIdCommitId(UUID projectId, UUID commitId, Http.Request request) {
        UUID pageAfter = Optional.ofNullable(request.getQueryString("page[after]"))
                .map(ElementController::fromCursor)
                //.map(UUID::fromString)
                .orElse(null);
        UUID pageBefore = Optional.ofNullable(request.getQueryString("page[before]"))
                .map(ElementController::fromCursor)
                //.map(UUID::fromString)
                .orElse(null);
        int pageSize = Optional.ofNullable(request.getQueryString("page[size]"))
                .map(Integer::parseInt)
                .orElse(100);
        if (pageSize <= 0) {
            return Results.badRequest("Page size must be greater than zero.");
        }

        List<Element> elements = elementService.getElementsByProjectIdCommitId(projectId, commitId, pageAfter, pageBefore, pageSize);
        boolean respondWithJsonLd = respondWithJsonLd(request);
        final String linkHeaderValue;
        if (elements.size() > 0) {
            boolean pageFull = elements.size() == pageSize;
            boolean hasNext = pageFull || pageBefore != null;
            boolean hasPrev = pageFull && pageBefore != null || pageAfter != null;
            // hasPrev -> !pageFull || pageAfter != null
            StringBuilder linkHeaderValueBuilder = new StringBuilder();
            if (hasNext) {
                linkHeaderValueBuilder.append(String.format("<http://%s/projects/%s/commits/%s/elements?page[after]=%s&page[size]=%s>; rel=\"next\"",
                        request.host(),
                        projectId,
                        commitId,
                        toCursor(elements.get(elements.size() - 1).getIdentifier()),
                        pageSize));
                if (hasPrev) {
                    linkHeaderValueBuilder.append(", ");
                }
            }
            if (hasPrev) {
                linkHeaderValueBuilder.append(String.format("<http://%s/projects/%s/commits/%s/elements?page[before]=%s&page[size]=%s>; rel=\"prev\"",
                        request.host(),
                        projectId,
                        commitId,
                        toCursor(elements.get(0).getIdentifier()),
                        pageSize));
            }
            linkHeaderValue = linkHeaderValueBuilder.length() > 0 ? linkHeaderValueBuilder.toString() : null;
        }
        else {
            linkHeaderValue = null;
        }

        return Optional.of(
                elements.stream()
                        .map(e -> respondWithJsonLd ?
                                adornMofObject(e, request, metamodelProvider, environment, projectId, commitId) :
                                e
                        )
                        .collect(Collectors.toList())
        )
                .map(collection -> JacksonHelper.collectionToTree(collection, List.class, respondWithJsonLd ?
                        JsonLdMofObjectAdornment.class :
                        metamodelProvider.getImplementationClass(Element.class))
                )
                .map(Results::ok)
                .map(result -> respondWithJsonLd ? result.as(JSONLD_MIME_TYPE) : result)
                .map(result -> linkHeaderValue != null ? result.withHeader("Link", linkHeaderValue) : result)
                .orElseThrow();
    }

    public Result getElementByProjectIdCommitIdElementId(UUID projectId, UUID commitId, UUID elementId, Http.Request request) {
        Optional<Element> element = elementService.getElementsByProjectIdCommitIdElementId(projectId, commitId, elementId);
        boolean respondWithJsonLd = respondWithJsonLd(request);
        return element
                .map(e -> respondWithJsonLd ? adornMofObject(e, request, metamodelProvider, environment, projectId, commitId) : e)
                .map(Json::toJson)
                .map(Results::ok)
                .map(result -> respondWithJsonLd ? result.as(JSONLD_MIME_TYPE) : result)
                .orElseGet(Results::notFound);

    }

    static JsonLdMofObjectAdornment adornMofObject(MofObject mof, Http.Request request, MetamodelProvider metamodelProvider, Environment environment, UUID projectId, UUID commitId) {
        return new JsonLdMofObjectAdornment(mof, metamodelProvider, environment,
                String.format("http://%s", request.host()),
                String.format("/projects/%s/commits/%s/elements/", projectId, commitId),
                INLINE_JSON_LD_CONTEXT
        );
    }

    public Result getRootsByProjectIdCommitId(UUID projectId, UUID commitId, Http.Request request) {
        List<Element> roots = elementService.getRootsByProjectIdCommitId(projectId, commitId);
        boolean respondWithJsonLd = respondWithJsonLd(request);
        return ok(JacksonHelper.collectionToTree(roots.stream()
                        .map(e -> respondWithJsonLd ? adornMofObject(e, request, metamodelProvider, environment, projectId, commitId) : e)
                        .collect(Collectors.toSet()), Set.class,
                respondWithJsonLd ? JsonLdMofObjectAdornment.class : metamodelProvider.getImplementationClass(Element.class)
        ));
    }

    static boolean respondWithJsonLd(Http.Request request) {
        return request.accepts(JSONLD_MIME_TYPE);
    }

    protected static char CURSOR_SEPARATOR = '|';

    protected static UUID fromCursor(String cursor) throws IllegalArgumentException {
        byte[] decoded = Base64.getUrlDecoder().decode(cursor);
        int separatorIndex = Bytes.indexOf(decoded, (byte) CURSOR_SEPARATOR);
        if (separatorIndex < 0) {
            throw new IllegalArgumentException("Cursor separator missing");
        }
        return UUID.fromString(
                new String(decoded, separatorIndex + 1, decoded.length - separatorIndex - 1)
        );
    }

    protected static String toCursor(UUID id) throws IllegalArgumentException {
        String unencoded = String.valueOf(Instant.now().toEpochMilli()) +
                CURSOR_SEPARATOR +
                id.toString();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(unencoded.getBytes());
    }
}
