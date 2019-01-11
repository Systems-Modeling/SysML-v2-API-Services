package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import models.Element;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.mvc.Results;
import services.ElementService;
import swagger.SwaggerUtils.ApiAction;

import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-10-24T16:52:32.143-07:00")

public class ElementsApiController extends JsonController {
    @Inject
    private ElementService service;

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result createElement() throws Exception {
        JsonNode nodebody = request().body().asJson();
        Element body;
        if (nodebody != null) {
            body = Json.fromJson(nodebody, Element.class);
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        Element obj = service.create(body);
        if (obj == null) {
            return badRequest();
        }
        JsonNode result = Json.toJson(obj);
        return created(result);
    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result createElementInModel(String modelId) throws Exception {
        JsonNode nodebody = request().body().asJson();
        Element body;
        if (nodebody != null) {
            body = Json.fromJson(nodebody, Element.class);
        }
        else {
            body = null;
        }
        UUID modelUUID;
        try {
            modelUUID = UUID.fromString(modelId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        Element obj = modelUUID.equals(body.getModel().getId()) ? service.create(body) : null;
        JsonNode result = Json.toJson(obj);
        return created(result);
    }

    @ApiAction
    public Result deleteElement(String id) throws Exception {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        service.delete(null, uuid);
        return noContent();
    }

    @ApiAction
    public Result deleteElementInModel(String modelId, String elementId) throws Exception {
        UUID modelUUID, elementUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            elementUUID = UUID.fromString(elementId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        service.delete(modelUUID, elementUUID);
        return noContent();
    }

    @ApiAction
    public Result deleteElements() throws Exception {
        service.deleteAll(null);
        return noContent();
    }

    @ApiAction
    public Result deleteElementsInModel(String modelId) throws Exception {
        UUID modelUUID;
        try {
            modelUUID = UUID.fromString(modelId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        service.deleteAll(modelUUID);
        return noContent();
    }

    @ApiAction
    public Result getElement(String id) throws Exception {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        Element obj = service.getById(null, uuid);
        if (obj == null) {
            return notFound();
        }
        JsonNode result = Json.toJson(obj);
        return ok(result);

    }

    @ApiAction
    public Result getElementInModel(String modelId, String elementId) throws Exception {
        UUID modelUUID, elementUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            elementUUID = UUID.fromString(elementId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        Element obj = service.getById(modelUUID, elementUUID);
        if (obj == null) {
            return notFound();
        }
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    public Result getElements() throws Exception {
        List<Element> obj = service.getAll(null);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    public Result getElementsInModel(String modelId) throws Exception {
        UUID modelUUID;
        try {
            modelUUID = UUID.fromString(modelId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        List<Element> obj = service.getAll(modelUUID);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateElement(String id) throws Exception {
        JsonNode nodebody = request().body().asJson();
        Element body;
        if (nodebody != null) {
            body = Json.fromJson(nodebody, Element.class);
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        Element obj = uuid.equals(body.getId()) ? service.update(body) : null;
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateElementInModel(String modelId, String elementId) throws Exception {
        JsonNode nodebody = request().body().asJson();
        Element body;
        if (nodebody != null) {
            body = Json.fromJson(nodebody, Element.class);
        }
        else {
            body = null;
        }
        UUID modelUUID, elementUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            elementUUID = UUID.fromString(elementId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        Element obj = elementUUID.equals(body.getId()) && modelUUID.equals(body.getModel().getId()) ? service.update(body) : null;
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateElements() throws Exception {
        JsonNode nodebody = request().body().asJson();
        List<Element> body;
        if (nodebody != null) {
            body = Json.mapper().readValue(nodebody.toString(), new TypeReference<List<Element>>() {
            });
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        List<Element> obj = service.updateAll(null, body);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateElementsInModel(String modelId) throws Exception {
        JsonNode nodebody = request().body().asJson();
        List<Element> body;
        if (nodebody != null) {
            body = Json.mapper().readValue(nodebody.toString(), new TypeReference<List<Element>>() {
            });
        }
        else {
            body = null;
        }
        UUID modelUUID;
        try {
            modelUUID = UUID.fromString(modelId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        for (Element element : body) {
            if (!modelUUID.equals(element.getModel().getId())) {
                return badRequest("modelId != model id of element " + element.getId());
            }
        }
        List<Element> obj = service.updateAll(modelUUID, body);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }
}
