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
import jackson.filter.AllowedPropertyFilter;
import jackson.filter.DynamicFilterMixin;
import jackson.jsonld.JsonLdAdorner;
import jackson.jsonld.MofObjectJsonLdAdorner;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.query.Query;
import play.Environment;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
import services.QueryService;

import javax.inject.Inject;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.StreamSupport;

public class QueryController extends JsonLdController<Element, MofObjectJsonLdAdorner.Parameters> {

    private final QueryService queryService;
    private final MetamodelProvider metamodelProvider;
    private final JsonLdAdorner<Element, MofObjectJsonLdAdorner.Parameters> adorner;

    @Inject
    public QueryController(QueryService queryService, MetamodelProvider metamodelProvider, Environment environment) {
        this.queryService = queryService;
        this.metamodelProvider = metamodelProvider;
        this.adorner = new MofObjectJsonLdAdorner<>(metamodelProvider, environment, INLINE_JSON_LD_CONTEXT);
    }

    public Result createWithProjectId(UUID projectId, Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Query requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Query.class));
        Optional<Query> response = queryService.create(projectId, requestedObject);
        return response.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }

    public Result byProject(UUID projectId, Request request) {
        PageRequest pageRequest = PageRequest.from(request);
        List<Query> queries = queryService.getByProjectId(projectId, pageRequest.getAfter(), pageRequest.getBefore(), pageRequest.getSize());
        return Optional.of(queries)
                .map(collection -> JacksonHelper.collectionToTree(collection, List.class, metamodelProvider.getImplementationClass(Query.class)))
                .map(Results::ok)
                .map(result -> paginateResult(
                        result,
                        queries.size(),
                        idx -> queries.get(idx).getId(),
                        request,
                        pageRequest
                ))
                .orElseThrow();
    }

    public Result byProjectAndId(UUID projectId, UUID queryId) {
        Optional<Query> query = queryService.getByProjectIdAndId(projectId, queryId);
        return query.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result getQueryResultsByProjectIdQueryId(UUID projectId, UUID queryId, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<UUID> commitId, Request request) {
        QueryService.QueryResults result = queryService.getQueryResultsByProjectIdQueryId(projectId, queryId, commitId.orElse(null));
        return buildResult(result, projectId, request);
    }

    public Result getQueryResultsByProjectIdQuery(UUID projectId, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<UUID> commitId, Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Query query = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Query.class));
        QueryService.QueryResults result = queryService.getQueryResultsByProjectIdQuery(projectId, query, commitId.orElse(null));
        return buildResult(result, projectId, request);
    }

    private Result buildResult(QueryService.QueryResults queryResults, UUID projectId, Request request) {
        List<Element> elements = queryResults.getElements();
        AllowedPropertyFilter filter = queryResults.getPropertyFilter();
        boolean ld = respondWithJsonLd(request);
        JsonNode json = buildJson(
                new HashSet<>(elements),
                Set.class,
                metamodelProvider.getImplementationClass(Element.class),
                request,
                new MofObjectJsonLdAdorner.Parameters(projectId, queryResults.getCommit().getId()),
                ld,
                filter != null ? Json.mapper().copy().addMixIn(MofObject.class, DynamicFilterMixin.class) : Json.mapper(),
                filter != null ? writer -> writer.with(new SimpleFilterProvider().addFilter(DynamicFilterMixin.FILTER_NAME, filter)) : UnaryOperator.identity()
        );
        // Workaround for JSON always containing "@type"
        if (filter != null && !filter.getAllowedProperties().contains("@type")) {
            StreamSupport.stream(Spliterators.spliteratorUnknownSize(json.elements(), Spliterator.ORDERED), false)
                    .filter(n -> n instanceof ObjectNode)
                    .forEach(n -> ((ObjectNode) n).remove("@type"));
        }
        return buildResult(json, ld);
    }

    @Override
    protected JsonLdAdorner<Element, MofObjectJsonLdAdorner.Parameters> getAdorner() {
        return adorner;
    }
}
