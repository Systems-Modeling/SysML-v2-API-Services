package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import dao.ElementDAO;
import kundera.JPAManager;
import models.Element;
import play.mvc.Result;
import play.mvc.Results;
import swagger.SwaggerUtils.ApiAction;

import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-10-24T16:52:32.143-07:00")

public class ElementsApiController extends JsonController {
    private static final String DOESNT_ACCEPT_JSON_MESSAGE = "Cannot process non-json requests.";
    private final ElementDAO dao;
    private final JPAManager kundera;

    @Inject
    private ElementsApiController(ElementDAO dao, JPAManager kundera) {
        this.dao = dao;
        this.kundera = kundera;
    }

    @ApiAction
    public Result createElement() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        Element body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), Element.class);
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        Element obj = dao.create(body);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return created(result);
    }

    @ApiAction
    public Result createElementInModel(String modelId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        Element body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), Element.class);
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
        Element obj = modelUUID.equals(body.getModel().getId()) ? dao.create(body) : null;
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return created(result);
    }

    @ApiAction
    public Result deleteElement(String id) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        dao.delete(null, uuid);
        return noContent();
    }

    @ApiAction
    public Result deleteElementInModel(String modelId, String elementId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID modelUUID, elementUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            elementUUID = UUID.fromString(elementId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        dao.delete(modelUUID, elementUUID);
        return noContent();
    }

    @ApiAction
    public Result deleteElements() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        dao.deleteAll(null);
        return noContent();
    }

    @ApiAction
    public Result deleteElementsInModel(String modelId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID modelUUID;
        try {
            modelUUID = UUID.fromString(modelId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        dao.deleteAll(modelUUID);
        return noContent();
    }

    @ApiAction
    public Result getElement(String id) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        Element obj = dao.getById(null, uuid);
        if (obj == null) {
            return notFound();
        }
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);

    }

    @ApiAction
    public Result getElementInModel(String modelId, String elementId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID modelUUID, elementUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            elementUUID = UUID.fromString(elementId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        Element obj = dao.getById(modelUUID, elementUUID);
        if (obj == null) {
            return notFound();
        }
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getElements() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        List<Element> obj = dao.getAll(null);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getElementsInModel(String modelId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID modelUUID;
        try {
            modelUUID = UUID.fromString(modelId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        List<Element> obj = dao.getAll(modelUUID);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result updateElement(String id) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        Element body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), Element.class);
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
        Element obj = uuid.equals(body.getId()) ? dao.update(body) : null;
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result updateElementInModel(String modelId, String elementId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        Element body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), Element.class);
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
        Element obj = elementUUID.equals(body.getId()) && modelUUID.equals(body.getModel().getId()) ? dao.update(body) : null;
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result updateElements() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        List<Element> body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), new TypeReference<List<Element>>() {
            });
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        List<Element> obj = dao.updateAll(null, body);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result updateElementsInModel(String modelId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        List<Element> body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), new TypeReference<List<Element>>() {
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
        List<Element> obj = dao.updateAll(modelUUID, body);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }
}
