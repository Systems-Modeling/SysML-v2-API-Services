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

/**
 * @author Manas Bajaj
 *
 * Controller for handling all API requests related to SysML v2 elements
 */
public class ProjectController extends Controller {
    @Inject
    private MetamodelProvider metamodelProvider;

    @Inject
    private ProjectService projectService;

    public Result byId(UUID id) {
        Optional<Project> project = projectService.getById(id);
        return project.map(m -> ok(Json.toJson(m))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Project> projects = projectService.getAll();
        return ok(JacksonHelper.collectionValueToTree(List.class, metamodelProvider.getImplementationClass(Project.class), projects));
    }

    public Result create(Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Project requestProject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Project.class));
        Optional<Project> responseProject = projectService.create(requestProject);
        return responseProject.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }
}
