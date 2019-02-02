package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Element;
import play.libs.Json;
import play.mvc.Controller;
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
    private ElementService elementService;

    public Result byId(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Element> element = elementService.getById(uuid);
        return element.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Element> elements = elementService.getAll();
        return ok(Json.toJson(elements));
    }

    public Result create() {
        JsonNode requestBodyJson = request().body().asJson();
        Element requestElement = Json.fromJson(requestBodyJson, Element.class);
        Optional<Element> responseElement = elementService.create(requestElement);
        return responseElement.map(e -> ok(Json.toJson(e))).orElseGet(Results::badRequest);
    }

    public Result byModelAndId(String elementId, String modelId) {
        UUID elementUuid = UUID.fromString(elementId);
        UUID modelUuid = UUID.fromString(modelId);
        Optional<Element> element = elementService.getByModelAndId(modelUuid, elementUuid);
        return element.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }
}
