package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import org.omg.sysml.query.Query;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.QueryService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class QueryController extends Controller {

    private final MetamodelProvider metamodelProvider;
    private final QueryService queryService;

    @Inject
    public QueryController(QueryService queryService, MetamodelProvider metamodelProvider) {
        this.queryService = queryService;
        this.metamodelProvider = metamodelProvider;
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
}
