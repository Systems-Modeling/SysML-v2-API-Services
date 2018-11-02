package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import models.Model;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.mvc.Results;
import services.ModelService;
import swagger.SwaggerUtils.ApiAction;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-10-24T16:52:32.143-07:00")

public class ModelsApiController extends JsonController {
    @Inject
    private ModelService service;

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result createModel() throws Exception {
        JsonNode nodebody = request().body().asJson();
        Model body;
        if (nodebody != null) {
            body = Json.fromJson(nodebody, Model.class);
        }
        else {
            body = null;
        }
        Model obj = service.create(body);
        JsonNode result = Json.toJson(obj);
        return created(result);
    }

    @ApiAction
    public Result deleteModel(String id) throws Exception {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        service.delete(uuid);
        return noContent();
    }

    @ApiAction
    public Result deleteModels() throws Exception {
        service.deleteAll();
        return noContent();
    }

    @ApiAction
    public Result getModel(String id) throws Exception {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        Model obj = service.getById(uuid);
        if (obj == null) {
            return notFound();
        }
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    public Result getModels() throws Exception {
        List<Model> obj = service.getAll();
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateModel(String id) throws Exception {
        JsonNode nodebody = request().body().asJson();
        Model body;
        if (nodebody != null) {
            body = Json.fromJson(nodebody, Model.class);
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
        Model obj = uuid.equals(body.getId()) ? service.update(body) : null;
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateModels() throws Exception {
        JsonNode nodebody = request().body().asJson();
        List<Model> body;
        if (nodebody != null) {
            body = Json.mapper().readValue(nodebody.toString(), new TypeReference<List<Model>>() {
            });
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        List<Model> obj = service.updateAll(body);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }
}
