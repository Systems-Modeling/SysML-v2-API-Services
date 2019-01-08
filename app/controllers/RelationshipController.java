package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Relationship;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.RelationshipService;

import javax.inject.Inject;
import java.util.Set;
import java.util.UUID;

/**
 * @author Manas Bajaj
 *
 * Controller for handling all API requests related to SysML v2 relationship elements
 */
public class RelationshipController extends Controller {

    private RelationshipService relationService;

    @Inject
    public RelationshipController(RelationshipService relationService) {
        this.relationService = relationService;
    }

    public Result byId(String id) {
        try {
            UUID relationshipId = UUID.fromString(id);
            Relationship relation = relationService.getById(relationshipId);
            return ok(Json.toJson(relation));
        }
        catch (IllegalArgumentException e) {
            return badRequest("Supplied identifier is not a UUID.");
        }
    }

    public Result byModel(String mid) {
        try {
            UUID modelId = UUID.fromString(mid);
            Set<Relationship> elements = relationService.getByModelId(modelId);
            return ok(Json.toJson(elements));
        }
        catch (IllegalArgumentException e) {
            return badRequest("Supplied identifier is not a UUID.");
        }
    }

    public Result byElementId(String id) {
        try {
            UUID elementId = UUID.fromString(id);
            Set<Relationship> relations = relationService.getByElementId(elementId);
            return ok(Json.toJson(relations));
        }
        catch (IllegalArgumentException e) {
            return badRequest("Supplied identifier is not a UUID.");
        }
    }

    public Result bySourceElementId(String id) {
        try {
            UUID elementId = UUID.fromString(id);
            Set<Relationship> relations = relationService.getBySourceElementId(elementId);
            return ok(Json.toJson(relations));
        }
        catch (IllegalArgumentException e) {
            return badRequest("Supplied identifier is not a UUID.");
        }
    }

    public Result byTargetElementId(String id) {
        try {
            UUID elementId = UUID.fromString(id);
            Set<Relationship> relations = relationService.getByTargetElementId(elementId);
            return ok(Json.toJson(relations));
        }
        catch (IllegalArgumentException e) {
            return badRequest("Supplied identifier is not a UUID.");
        }
    }


    public Result all() {
        Set<Relationship> relations = relationService.getAll();
        return ok(Json.toJson(relations));
    }

    public Result create() {
        JsonNode requestBodyJson = request().body().asJson();
        Relationship newRelation = Json.fromJson(requestBodyJson, Relationship.class);
        Relationship createdRelation = relationService.create(newRelation);
        if(createdRelation!=null)
            return created(Json.toJson(createdRelation));
        else
            return badRequest("Element with the following specification could not be created. \n " + requestBodyJson);
    }
}
