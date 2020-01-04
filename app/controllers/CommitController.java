package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import org.omg.sysml.lifecycle.Commit;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.CommitService;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CommitController extends Controller {
    @Inject
    private MetamodelProvider metamodelProvider;

    @Inject
    private CommitService commitService;

    public Result byId(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Commit> commit = commitService.getById(uuid);
        return commit.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Commit> commits = commitService.getAll();
        return ok(JacksonHelper.collectionValueToTree(List.class, metamodelProvider.getImplementationClass(Commit.class), commits));
    }

    public Result create(Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Commit requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Commit.class));
        if (requestedObject.getId() != null || requestedObject.getTimestamp() != null) {
            return Results.badRequest();
        }
        requestedObject.setTimestamp(ZonedDateTime.now());
        Optional<Commit> responseCommit = commitService.create(requestedObject);
        return responseCommit.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }

    public Result createWithProjectId(UUID projectId, Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Commit requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Commit.class));
        if (requestedObject.getId() != null || requestedObject.getTimestamp() != null) {
            return Results.badRequest();
        }
        requestedObject.setTimestamp(ZonedDateTime.now());
        Optional<Commit> responseCommit = commitService.create(projectId, requestedObject);
        return responseCommit.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }

    public Result byProject(String projectId) {
        UUID projectUuid = UUID.fromString(projectId);
        List<Commit> commits = commitService.getByProjectId(projectUuid);
        return ok(JacksonHelper.collectionValueToTree(List.class, metamodelProvider.getImplementationClass(Commit.class), commits));
    }

    public Result byProjectAndId(String commitId, String projectId) {
        UUID commitUuid = UUID.fromString(commitId);
        UUID projectUuid = UUID.fromString(projectId);
        Optional<Commit> commit = commitService.getByProjectIdAndId(projectUuid, commitUuid);
        return commit.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }
}
