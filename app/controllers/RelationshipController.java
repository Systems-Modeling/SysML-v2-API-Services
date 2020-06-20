package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import jackson.JsonLdMofObjectAdornment;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.utils.RelationshipDirection;
import play.Environment;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.RelationshipService;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Manas Bajaj
 * <p>
 * Controller for handling all API requests related to SysML v2 elements
 */
public class RelationshipController extends Controller {
    @Inject
    private MetamodelProvider metamodelProvider;

    @Inject
    private RelationshipService relationshipService;

    @Inject
    private Environment environment;

    public Result byId(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Relationship> relationship = relationshipService.getById(uuid);
        return relationship.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Relationship> relationships = relationshipService.getAll();
        return ok(JacksonHelper.collectionValueToTree(List.class, metamodelProvider.getImplementationClass(Relationship.class), relationships));
    }

    public Result create(Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        MofObject requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(MofObject.class));
        if (!(requestedObject instanceof Relationship)) {
            return Results.badRequest();
        }
        Optional<Relationship> responseRelationship = relationshipService.create((Relationship) requestedObject);
        return responseRelationship.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }

    public Result getRelationshipsByProjectIdCommitIdRelatedElementId(UUID projectId, UUID commitId, UUID elementId, Optional<String> direction, Http.Request request) {
        RelationshipDirection relDirection = direction
                .flatMap(d -> Arrays.stream(RelationshipDirection.values())
                        .filter(rd -> rd.toString().equalsIgnoreCase(d))
                        .findAny())
                .orElse(RelationshipDirection.BOTH);

        Set<Relationship> relationships = relationshipService.getRelationshipsByProjectCommitRelatedElement(projectId, commitId, elementId, relDirection);
        boolean respondWithJsonLd = ElementController.respondWithJsonLd(request);
        return ok(JacksonHelper.collectionValueToTree(Set.class,
                respondWithJsonLd ? JsonLdMofObjectAdornment.class : metamodelProvider.getImplementationClass(Relationship.class),
                relationships.stream().
                        map(r -> respondWithJsonLd ? ElementController.adornMofObject((MofObject) r, request, metamodelProvider, environment, projectId, commitId) : r)
                        .collect(Collectors.toSet())
        ));
    }
}
