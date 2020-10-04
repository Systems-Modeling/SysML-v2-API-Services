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

public class QueryController extends Controller {

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

    public Result getQueryResultsByProjectIdCommitIdQueryId(UUID projectId, UUID commitId, UUID queryId, Http.Request request) {
        Map.Entry<Set<Element>, AllowedPropertyFilter> results = queryService.getQueryResultsByProjectIdCommitIdQueryId(projectId, commitId, queryId);
        return buildResponse(results, projectId, commitId, request);
    }

    public Result getQueryResultsByProjectIdCommitIdQuery(UUID projectId, UUID commitId, Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Query query = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Query.class));
        Map.Entry<Set<Element>, AllowedPropertyFilter> results = queryService.getQueryResultsByProjectIdCommitIdQuery(projectId, commitId, query);
        return buildResponse(results, projectId, commitId, request);
    }

    private Result buildResponse(Map.Entry<Set<Element>, AllowedPropertyFilter> results, UUID projectId, UUID commitId, Http.Request request) {
        Set<Element> elements = results.getKey();
        AllowedPropertyFilter filter = results.getValue();
        boolean respondWithJsonLd = ElementController.respondWithJsonLd(request);
        JsonNode json = JacksonHelper.collectionToTree(elements.stream()
                        .map(e -> respondWithJsonLd ? ElementController.adornMofObject(e, request, metamodelProvider, environment, projectId, commitId) : e)
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
