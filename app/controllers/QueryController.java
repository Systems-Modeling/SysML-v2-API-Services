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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import jackson.JsonLdMofObjectAdornment;
import jackson.filter.AllowedPropertyFilter;
import jackson.filter.DynamicFilterMixin;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.query.Query;
import play.Environment;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.QueryService;

import javax.inject.Inject;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class QueryController extends BaseController {

    private final MetamodelProvider metamodelProvider;
    private final QueryService queryService;
    private final Environment environment;

    @Inject
    public QueryController(QueryService queryService, MetamodelProvider metamodelProvider, Environment environment) {
        this.queryService = queryService;
        this.metamodelProvider = metamodelProvider;
        this.environment = environment;
    }

    public Result byId(UUID id) {
        Optional<Query> query = queryService.getById(id);
        return query.map(m -> ok(Json.toJson(m))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Query> queries = queryService.getAll();
        return ok(JacksonHelper.collectionToTree(queries, List.class, metamodelProvider.getImplementationClass(Query.class)));
    }

    public Result create(Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Query requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Query.class));
        Optional<Query> response = queryService.create(requestedObject);
        return response.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }

    public Result createWithProjectId(UUID projectId, Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Query requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Query.class));
        Optional<Query> response = queryService.create(projectId, requestedObject);
        return response.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }

    public Result byProject(UUID projectId) {
        List<Query> queries = queryService.getByProjectId(projectId);
        return ok(JacksonHelper.collectionToTree(queries, List.class, metamodelProvider.getImplementationClass(Query.class)));
    }

    public Result byProjectAndId(UUID projectId, UUID queryId) {
        Optional<Query> query = queryService.getByProjectIdAndId(projectId, queryId);
        return query.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result getQueryResultsByProjectIdQueryId(UUID projectId, UUID queryId, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<UUID> commitId, Http.Request request) {
        QueryService.QueryResults result = queryService.getQueryResultsByProjectIdQueryId(projectId, queryId, commitId.orElse(null));
        return buildResponse(result, projectId, request);
    }

    public Result getQueryResultsByProjectIdQuery(UUID projectId, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<UUID> commitId, Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Query query = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Query.class));
        QueryService.QueryResults result = queryService.getQueryResultsByProjectIdQuery(projectId, query, commitId.orElse(null));
        return buildResponse(result, projectId, request);
    }

    private Result buildResponse(QueryService.QueryResults result, UUID projectId, Http.Request request) {
        List<Element> elements = result.getElements();
        AllowedPropertyFilter filter = result.getPropertyFilter();
        boolean respondWithJsonLd = respondWithJsonLd(request);
        JsonNode json = JacksonHelper.collectionToTree(elements.stream()
                        .map(e -> respondWithJsonLd ? adornMofObject(e, request, metamodelProvider, environment, projectId, result.getCommit().getId()) : e)
                        .collect(Collectors.toSet()), Set.class,
                respondWithJsonLd ? JsonLdMofObjectAdornment.class : metamodelProvider.getImplementationClass(Element.class),
                filter != null ? () -> Json.mapper().copy().addMixIn(MofObject.class, DynamicFilterMixin.class) : Json::mapper,
                filter != null ? writer -> writer.with(new SimpleFilterProvider().addFilter(DynamicFilterMixin.FILTER_NAME, filter)) : UnaryOperator.identity()
        );
        // Workaround for JSON always containing "@type"
        if (filter != null && !filter.getAllowedProperties().contains("@type")) {
            StreamSupport.stream(Spliterators.spliteratorUnknownSize(json.elements(), Spliterator.ORDERED), false)
                    .filter(n -> n instanceof ObjectNode)
                    .forEach(n -> ((ObjectNode) n).remove("@type"));
        }
        return ok(json);
    }
}
