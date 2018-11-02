package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import models.Relationship;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import play.mvc.Results;
import services.RelationshipService;
import swagger.SwaggerUtils.ApiAction;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-10-24T16:52:32.143-07:00")

public class RelationshipsApiController extends JsonController {
    @Inject
    private RelationshipService service;

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result createRelationship() throws Exception {
        JsonNode nodebody = request().body().asJson();
        Relationship body;
        if (nodebody != null) {
            body = Json.fromJson(nodebody, Relationship.class);
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        Relationship obj = service.create(body);
        JsonNode result = Json.toJson(obj);
        return created(result);
    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result createRelationshipInModel(String modelId) throws Exception {
        JsonNode nodebody = request().body().asJson();
        Relationship body;
        if (nodebody != null) {
            body = Json.fromJson(nodebody, Relationship.class);
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
        Relationship obj = modelUUID.equals(body.getModel().getId()) ? service.create(body) : null;
        JsonNode result = Json.toJson(obj);
        return created(result);

    }

    @ApiAction
    public Result deleteRelationship(String id) throws Exception {
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
    public Result deleteRelationshipInModel(String modelId, String relationshipId) throws Exception {
        UUID modelUUID, relationshipUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            relationshipUUID = UUID.fromString(relationshipId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        service.delete(modelUUID, relationshipUUID);
        return noContent();

    }

    @ApiAction
    public Result deleteRelationships() throws Exception {
        service.deleteAll(null);
        return noContent();
    }

    @ApiAction
    public Result deleteRelationshipsInModel(String modelId) throws Exception {
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
    public Result getRelationship(String id) throws Exception {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        Relationship obj = service.getById(null, uuid);
        if (obj == null) {
            return notFound();
        }
        JsonNode result = Json.toJson(obj);
        return ok(result);

    }

    @ApiAction
    public Result getRelationshipInModel(String modelId, String relationshipId) throws Exception {
        UUID modelUUID, relationshipUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            relationshipUUID = UUID.fromString(relationshipId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        Relationship obj = service.getById(modelUUID, relationshipUUID);
        if (obj == null) {
            return notFound();
        }
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    public Result getRelationships() throws Exception {
        List<Relationship> obj = service.getAll(null);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    public Result getRelationshipsBySource(String sourceId) throws Exception {
        UUID sourceUUID;
        try {
            sourceUUID = UUID.fromString(sourceId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        List<Relationship> obj = service.getBySourceId(null, sourceUUID);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    public Result getRelationshipsByTarget(String targetId) throws Exception {
        UUID targetUUID;
        try {
            targetUUID = UUID.fromString(targetId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        List<Relationship> obj = service.getByTargetId(null, targetUUID);
        JsonNode result = Json.toJson(obj);
        return ok(result);

    }

    @ApiAction
    public Result getRelationshipsInModel(String modelId) throws Exception {
        UUID modelUUID;
        try {
            modelUUID = UUID.fromString(modelId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        List<Relationship> obj = service.getAll(modelUUID);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    public Result getRelationshipsInModelBySource(String modelId, String sourceId) throws Exception {
        UUID modelUUID, sourceUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            sourceUUID = UUID.fromString(sourceId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        List<Relationship> obj = service.getBySourceId(modelUUID, sourceUUID);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    public Result getRelationshipsInModelByTarget(String modelId, String targetId) throws Exception {
        UUID modelUUID, targetUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            targetUUID = UUID.fromString(targetId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        List<Relationship> obj = service.getByTargetId(modelUUID, targetUUID);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateRelationship(String id) throws Exception {
        JsonNode nodebody = request().body().asJson();
        Relationship body;
        if (nodebody != null) {
            body = Json.fromJson(nodebody, Relationship.class);
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
        Relationship obj = uuid.equals(body.getId()) ? service.update(body) : null;
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateRelationshipInModel(String modelId, String relationshipId) throws Exception {
        JsonNode nodebody = request().body().asJson();
        Relationship body;
        if (nodebody != null) {
            body = Json.fromJson(nodebody, Relationship.class);
        }
        else {
            body = null;
        }
        UUID modelUUID, relationshipUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            relationshipUUID = UUID.fromString(relationshipId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        Relationship obj = relationshipUUID.equals(body.getId()) && relationshipUUID.equals(body.getModel().getId()) ? service.update(body) : null;
        JsonNode result = Json.toJson(obj);
        return ok(result);

    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateRelationships() throws Exception {
        JsonNode nodebody = request().body().asJson();
        List<Relationship> body;
        if (nodebody != null) {
            body = Json.mapper().readValue(nodebody.toString(), new TypeReference<List<Relationship>>() {
            });
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        List<Relationship> obj = service.updateAll(null, body);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }

    @ApiAction
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateRelationshipsInModel(String modelId) throws Exception {
        JsonNode nodebody = request().body().asJson();
        List<Relationship> body;
        if (nodebody != null) {
            body = Json.mapper().readValue(nodebody.toString(), new TypeReference<List<Relationship>>() {
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
        for (Relationship relationship : body) {
            if (!modelUUID.equals(relationship.getModel().getId())) {
                return badRequest("modelId != model id of relationship " + relationship.getId());
            }
        }
        List<Relationship> obj = service.updateAll(modelUUID, body);
        JsonNode result = Json.toJson(obj);
        return ok(result);
    }
}
