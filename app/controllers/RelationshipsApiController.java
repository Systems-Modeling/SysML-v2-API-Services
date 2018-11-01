package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import dao.RelationshipDAO;
import kundera.JPAManager;
import models.Relationship;
import play.mvc.Result;
import play.mvc.Results;
import swagger.SwaggerUtils.ApiAction;

import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPlayFrameworkCodegen", date = "2018-10-24T16:52:32.143-07:00")

public class RelationshipsApiController extends JsonController {
    private static final String DOESNT_ACCEPT_JSON_MESSAGE = "Cannot process non-json requests.";
    private final RelationshipDAO dao;
    private final JPAManager kundera;

    @Inject
    private RelationshipsApiController(RelationshipDAO dao, JPAManager kundera) {
        this.dao = dao;
        this.kundera = kundera;
    }

    @ApiAction
    public Result createRelationship() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        Relationship body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), Relationship.class);
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        Relationship obj = dao.create(body);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return created(result);
    }

    @ApiAction
    public Result createRelationshipInModel(String modelId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        Relationship body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), Relationship.class);
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
        Relationship obj = modelUUID.equals(body.getModel().getId()) ? dao.create(body) : null;
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return created(result);

    }

    @ApiAction
    public Result deleteRelationship(String id) throws Exception {
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
    public Result deleteRelationshipInModel(String modelId, String relationshipId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID modelUUID, relationshipUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            relationshipUUID = UUID.fromString(relationshipId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        dao.delete(modelUUID, relationshipUUID);
        return noContent();

    }

    @ApiAction
    public Result deleteRelationships() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        dao.deleteAll(null);
        return noContent();
    }

    @ApiAction
    public Result deleteRelationshipsInModel(String modelId) throws Exception {
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
    public Result getRelationship(String id) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        Relationship obj = dao.getById(null, uuid);
        if (obj == null) {
            return notFound();
        }
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);

    }

    @ApiAction
    public Result getRelationshipInModel(String modelId, String relationshipId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID modelUUID, relationshipUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            relationshipUUID = UUID.fromString(relationshipId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        Relationship obj = dao.getById(modelUUID, relationshipUUID);
        if (obj == null) {
            return notFound();
        }
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getRelationships() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        List<Relationship> obj = dao.getAll(null);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getRelationshipsBySource(String sourceId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID sourceUUID;
        try {
            sourceUUID = UUID.fromString(sourceId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        List<Relationship> obj = dao.getBySourceId(null, sourceUUID);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getRelationshipsByTarget(String targetId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID targetUUID;
        try {
            targetUUID = UUID.fromString(targetId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        List<Relationship> obj = dao.getByTargetId(null, targetUUID);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);

    }

    @ApiAction
    public Result getRelationshipsInModel(String modelId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID modelUUID;
        try {
            modelUUID = UUID.fromString(modelId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided id is not a valid UUID.", Results::badRequest);
        }
        List<Relationship> obj = dao.getAll(modelUUID);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getRelationshipsInModelBySource(String modelId, String sourceId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID modelUUID, sourceUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            sourceUUID = UUID.fromString(sourceId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        List<Relationship> obj = dao.getBySourceId(modelUUID, sourceUUID);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result getRelationshipsInModelByTarget(String modelId, String targetId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        UUID modelUUID, targetUUID;
        try {
            modelUUID = UUID.fromString(modelId);
            targetUUID = UUID.fromString(targetId);
        } catch (IllegalArgumentException iae) {
            return getErrorStringAsJsonResult("The provided ids are not valid UUIDs.", Results::badRequest);
        }
        List<Relationship> obj = dao.getByTargetId(modelUUID, targetUUID);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result updateRelationship(String id) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        Relationship body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), Relationship.class);
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
        Relationship obj = uuid.equals(body.getId()) ? dao.update(body) : null;
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result updateRelationshipInModel(String modelId, String relationshipId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        Relationship body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), Relationship.class);
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
        Relationship obj = relationshipUUID.equals(body.getId()) && relationshipUUID.equals(body.getModel().getId()) ? dao.update(body) : null;
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);

    }

    @ApiAction
    public Result updateRelationships() throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        List<Relationship> body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), new TypeReference<List<Relationship>>() {
            });
        }
        else {
            throw new IllegalArgumentException("'body' parameter is required");
        }
        List<Relationship> obj = dao.updateAll(null, body);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }

    @ApiAction
    public Result updateRelationshipsInModel(String modelId) throws Exception {
        if (!acceptsJson(request())) {
            return getErrorStringAsJsonResult(DOESNT_ACCEPT_JSON_MESSAGE, Results::unsupportedMediaType);
        }
        JsonNode nodebody = request().body().asJson();
        List<Relationship> body;
        if (nodebody != null) {
            body = kundera.getObjectMapper().readValue(nodebody.toString(), new TypeReference<List<Relationship>>() {
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
        List<Relationship> obj = dao.updateAll(modelUUID, body);
        JsonNode result = kundera.getObjectMapper().valueToTree(obj);
        return ok(result);
    }
}
