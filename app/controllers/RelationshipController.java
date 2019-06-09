package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import config.MetamodelProvider;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.metamodel.Relationship;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.RelationshipService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Controller for handling all API requests related to SysML v2 elements
 */
public class RelationshipController extends Controller {
    @Inject
    private MetamodelProvider metamodelProvider;

    @Inject
    private RelationshipService relationshipService;

    public Result byId(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Relationship> relationship = relationshipService.getById(uuid);
        return relationship.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Relationship> relationships = relationshipService.getAll();
        return ok(Json.toJson(relationships));
    }

    public Result create(Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        MofObject requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(MofObject.class));
        if (!(requestedObject instanceof Relationship)) {
            return Results.badRequest();
        }
        Optional<Relationship> responseRelationship = relationshipService.create((Relationship) requestedObject);
        return responseRelationship.map(e -> created(Json.toJson(e))).orElseGet(Results::badRequest);
    }

    public Result byRelatedElementId(String id) {
        UUID elementUuid = UUID.fromString(id);
        List<Relationship> relationships = relationshipService.getByRelatedElementId(elementUuid);
        return ok(Json.toJson(relationships));
    }

    public Result bySourceElementId(String id) {
        UUID elementUuid = UUID.fromString(id);
        List<Relationship> relationships = relationshipService.getBySourceElementId(elementUuid);
        return ok(Json.toJson(relationships));
    }

    public Result byTargetElementId(String id) {
        UUID elementUuid = UUID.fromString(id);
        List<Relationship> relationships = relationshipService.getByTargetElementId(elementUuid);
        return ok(Json.toJson(relationships));
    }

    public Result byModel(String modelId) {
        UUID modelUuid = UUID.fromString(modelId);
        List<Relationship> relationships = relationshipService.getByModelId(modelUuid);
        return ok(Json.toJson(relationships));
    }
}
