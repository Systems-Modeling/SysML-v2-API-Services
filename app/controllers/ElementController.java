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

import config.MetamodelProvider;
import jackson.JacksonHelper;
import jackson.JsonLdMofObjectAdornment;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.MofObject;
import play.Environment;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.ElementService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static jackson.JsonLdMofObjectAdornment.JSONLD_MIME_TYPE;

public class ElementController extends BaseController {

    private static final boolean INLINE_JSON_LD_CONTEXT_DEFAULT = true;
    private static final boolean INLINE_JSON_LD_CONTEXT = Optional.ofNullable(System.getenv("INLINE_JSON_LD_CONTEXT"))
            .map(Boolean::parseBoolean)
            .orElse(INLINE_JSON_LD_CONTEXT_DEFAULT);

    private final ElementService elementService;
    private final MetamodelProvider metamodelProvider;
    private final Environment environment;

    @Inject
    public ElementController(ElementService elementService, MetamodelProvider metamodelProvider, Environment environment) {
        this.elementService = elementService;
        this.metamodelProvider = metamodelProvider;
        this.environment = environment;
    }

    public Result getElementsByProjectIdCommitId(UUID projectId, UUID commitId, Http.Request request) {
        PageRequest pageRequest = PageRequest.from(request);
        List<Element> elements = elementService.getElementsByProjectIdCommitId(projectId, commitId, pageRequest.getAfter(), pageRequest.getBefore(), pageRequest.getSize());
        return buildResult(elements, projectId, commitId, request, pageRequest);
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

    public Result getRootsByProjectIdCommitId(UUID projectId, UUID commitId, Http.Request request) {
        PageRequest pageRequest = PageRequest.from(request);
        List<Element> roots = elementService.getRootsByProjectIdCommitId(projectId, commitId, pageRequest.getAfter(), pageRequest.getBefore(), pageRequest.getSize());
        return buildResult(roots, projectId, commitId, request, pageRequest);
    }

    protected Result buildResult(List<Element> elements, UUID projectId, UUID commitId, Http.Request request, PageRequest pageRequest) {
        boolean respondWithJsonLd = respondWithJsonLd(request);
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
                .map(result -> paginateResult(
                        result,
                        elements.size(),
                        idx -> elements.get(idx).getIdentifier(),
                        request,
                        pageRequest
                ))
                .orElseThrow();
    }

    static JsonLdMofObjectAdornment adornMofObject(MofObject mof, Http.Request request, MetamodelProvider metamodelProvider, Environment environment, UUID projectId, UUID commitId) {
        return new JsonLdMofObjectAdornment(mof, metamodelProvider, environment,
                String.format("http://%s", request.host()),
                String.format("/projects/%s/commits/%s/elements/", projectId, commitId),
                INLINE_JSON_LD_CONTEXT
        );
    }

    static boolean respondWithJsonLd(Http.Request request) {
        return request.accepts(JSONLD_MIME_TYPE);
    }
}
