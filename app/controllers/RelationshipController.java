/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020  InterCAX LLC
 * Copyright (C) 2020  California Institute of Technology ("Caltech")
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 */

package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.utils.RelationshipDirection;
import play.Environment;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.RelationshipService;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RelationshipController extends BaseController {

    private final RelationshipService relationshipService;
    private final MetamodelProvider metamodelProvider;
    private final Environment environment;

    @Inject
    public RelationshipController(RelationshipService relationshipService, MetamodelProvider metamodelProvider, Environment environment) {
        this.relationshipService = relationshipService;
        this.metamodelProvider = metamodelProvider;
        this.environment = environment;
    }

    public Result byId(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Relationship> relationship = relationshipService.getById(uuid);
        return relationship.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Relationship> relationships = relationshipService.getAll();
        return ok(JacksonHelper.collectionToTree(relationships, List.class, metamodelProvider.getImplementationClass(Relationship.class)));
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

    public Result getRelationshipsByProjectIdCommitIdRelatedElementId(UUID projectId, UUID commitId, UUID elementId, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<String> direction, Http.Request request) {
        PageRequest pageRequest = PageRequest.from(request);
        RelationshipDirection relationshipDirection = direction
                .flatMap(d -> Arrays.stream(RelationshipDirection.values())
                        .filter(rd -> rd.toString().equalsIgnoreCase(d))
                        .findAny())
                .orElse(RelationshipDirection.BOTH);

        List<Relationship> relationships = relationshipService.getRelationshipsByProjectCommitRelatedElement(
                projectId,
                commitId,
                elementId,
                relationshipDirection,
                pageRequest.getAfter(),
                pageRequest.getBefore(),
                pageRequest.getSize()
        );
        return buildResult(relationships, projectId, commitId, request, pageRequest);
    }

    private Result buildResult(List<Relationship> relationships, UUID projectId, UUID commitId, Http.Request request, PageRequest pageRequest) {
        return buildResult(relationships, Relationship.class, Relationship::getIdentifier, projectId, commitId, request, pageRequest, metamodelProvider, environment);
    }
}
