package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.impl.CommitImpl;
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

    private final MetamodelProvider metamodelProvider;
    private final CommitService commitService;

    @Inject
    public CommitController(CommitService commitService, MetamodelProvider metamodelProvider) {
        this.commitService = commitService;
        this.metamodelProvider = metamodelProvider;
    }

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
        Optional<Commit> response = commitService.create(projectId, requestedObject);
        return response.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }

    public Result byProject(UUID projectId) {
        List<Commit> commits = commitService.getByProjectId(projectId);
        return ok(JacksonHelper.collectionValueToTree(List.class, metamodelProvider.getImplementationClass(Commit.class), commits, writer -> writer.withView(CommitImpl.Views.Compact.class)));
    }

    public Result byProjectAndId(UUID projectId, UUID commitId) {
        Optional<Commit> commit = commitService.getByProjectIdAndId(projectId, commitId);
        return commit.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result headByProject(UUID projectId) {
        Optional<Commit> commit = commitService.getHeadByProjectId(projectId);
        return commit.map(e -> ok(JacksonHelper.valueToTree(commit, writer -> writer.withView(CommitImpl.Views.Compact.class)))).orElseGet(Results::notFound);
    }
}
