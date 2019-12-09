package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.MofObject;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.ElementService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Manas Bajaj
 * <p>
 * Controller for handling all API requests related to SysML v2 elements
 */
public class ElementController extends Controller {
    @Inject
    private MetamodelProvider metamodelProvider;

    @Inject
    private ElementService elementService;

    public Result byId(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Element> element = elementService.getById(uuid);
        return element.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Element> elements = elementService.getAll();
        return ok(JacksonHelper.collectionValueToTree(List.class, metamodelProvider.getImplementationClass(Element.class), elements));
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

    public Result byProject(String projectId) {
        UUID projectUuid = UUID.fromString(projectId);
        List<Element> elements = elementService.getByProjectId(projectUuid);
        return ok(JacksonHelper.collectionValueToTree(List.class, metamodelProvider.getImplementationClass(Element.class), elements));
    }

    public Result byProjectAndId(String elementId, String projectId) {
        UUID elementUuid = UUID.fromString(elementId);
        UUID projectUuid = UUID.fromString(projectId);
        Optional<Element> element = elementService.getByProjectIdAndId(projectUuid, elementUuid);
        return element.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }
}
