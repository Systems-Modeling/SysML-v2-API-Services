package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import dao.ModelDAO;
import kundera.JPAManager;
import models.Model;
import play.mvc.Result;
import play.mvc.Results;
import swagger.SwaggerUtils.ApiAction;

import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-10-24T16:52:32.143-07:00")

public class ModelsApiController extends JsonController {
    private static final String DOESNT_ACCEPT_JSON_MESSAGE = "Cannot process non-json requests.";

    private final ModelDAO dao;
    private final JPAManager jpa;

    @Inject
    private ModelsApiController(ModelDAO dao, JPAManager jpa) {
        this.dao = dao;
        this.jpa = jpa;
    }

    @ApiAction
    public Result createModel() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        Model body;
        if (nodebody != null) {
            body = jpa.getObjectMapper().readValue(nodebody.toString(), Model.class);
        }
        else {
            body = null;
        }
        Model obj = dao.create(body);
        JsonNode result = jpa.getObjectMapper().valueToTree(obj);
        return created(result);
    }

    @ApiAction
    public Result deleteModel(String id) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        dao.delete(uuid);
        return noContent();
    }

    @ApiAction
    public Result deleteModels() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        dao.deleteAll();
        return noContent();
    }

    @ApiAction
    public Result getModel(String id) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        Model obj = dao.getById(uuid);
        if (obj == null) {
            return notFound();
        }
        JsonNode result = jpa.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getModels() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        List<Model> obj = dao.getAll();
        JsonNode result = jpa.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result updateModel(String id) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        Model body;
        if (nodebody != null) {
            body = jpa.getObjectMapper().readValue(nodebody.toString(), Model.class);
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        Model obj = uuid.equals(body.getId()) ? dao.update(body) : null;
        JsonNode result = jpa.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result updateModels() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        List<Model> body;
        if (nodebody != null) {
            body = jpa.getObjectMapper().readValue(nodebody.toString(), new TypeReference<List<Model>>() {
            });
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        List<Model> obj = dao.updateAll(body);
        JsonNode result = jpa.getObjectMapper().valueToTree(obj);
        return ok(result);
    }
}
