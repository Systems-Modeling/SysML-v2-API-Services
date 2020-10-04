package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import org.omg.sysml.lifecycle.Project;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.ProjectService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjectController extends Controller {

    private final MetamodelProvider metamodelProvider;
    private final ProjectService projectService;

    @Inject
    public ProjectController(ProjectService projectService, MetamodelProvider metamodelProvider) {
        this.projectService = projectService;
        this.metamodelProvider = metamodelProvider;
    }

    public Result byId(UUID id) {
        Optional<Project> project = projectService.getById(id);
        return project.map(m -> ok(Json.toJson(m))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Project> projects = projectService.getAll();
        return ok(JacksonHelper.collectionToTree(projects, List.class, metamodelProvider.getImplementationClass(Project.class)));
    }

    public Result create(Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Project requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Project.class));
        Optional<Project> response = projectService.create(requestedObject);
        return response.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }
}
