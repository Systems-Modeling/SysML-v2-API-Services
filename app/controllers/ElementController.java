package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Element;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ElementService;

import javax.inject.Inject;
import java.util.Set;
import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Controller for handling all API requests related to SysML v2 elements
 */
public class ElementController extends Controller {

    private ElementService elementService;

    @Inject
    public ElementController(ElementService elementService) {
        this.elementService = elementService;
    }

    public Result byId(String id) {
        try {
            UUID elementId = UUID.fromString(id);
            Element element = elementService.getById(elementId);
            return ok(Json.toJson(element));
        }
        catch (IllegalArgumentException e) {
            return badRequest("Supplied identifier is not a UUID.");
        }
    }

    public Result byModel(String mid) {
        try {
            UUID modelId = UUID.fromString(mid);
            Set<Element> elements = elementService.getByModelId(modelId);
            return ok(Json.toJson(elements));
        }
        catch (IllegalArgumentException e) {
            return badRequest("Supplied identifier is not a UUID.");
        }
    }

    public Result byIdAndModel(String eid, String mid) {
        try {
            UUID elementId = UUID.fromString(eid);
            UUID modelId = UUID.fromString(mid);
            Element element = elementService.getById(modelId, elementId);
            if(element!=null)
                return ok(Json.toJson(element));
            else
                return notFound("Element with id " + eid + " cannot be found in model with id " + mid);
        }
        catch (IllegalArgumentException e) {
            return badRequest("Supplied identifiers are not UUIDs.");
        }
    }

    public Result all() {
        Set<Element> elements = elementService.getAll();
        return ok(Json.toJson(elements));
    }

    public Result create() {
        JsonNode requestBodyJson = request().body().asJson();
        Element newElement = Json.fromJson(requestBodyJson, Element.class);
        Element createdElement = elementService.create(newElement);
        if(createdElement!=null)
            return created(Json.toJson(createdElement));
        else
            return badRequest("Element with the following specification could not be created. \n " + requestBodyJson);
    }
}
