package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Model;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.ModelService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Controller for handling all API requests related to SysML v2 elements
 */
public class ModelController extends Controller {

    @Inject
    private ModelService modelService;

    public Result byId(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Model> model = modelService.getById(uuid);
        return model.map(m -> ok(Json.toJson(m))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Model> models = modelService.getAll();
        return ok(Json.toJson(models));
    }

    public Result create() {
        JsonNode requestBodyJson = request().body().asJson();
        Model requestModel = Json.fromJson(requestBodyJson, Model.class);
        Optional<Model> responseModel = modelService.create(requestModel);
        return responseModel.map(e -> ok(Json.toJson(e))).orElseGet(Results::badRequest);
    }
}
