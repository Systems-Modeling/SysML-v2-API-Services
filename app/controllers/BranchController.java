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
import org.omg.sysml.lifecycle.Branch;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.BranchService;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BranchController extends BaseController {

    private final MetamodelProvider metamodelProvider;
    private final BranchService branchService;

    @Inject
    public BranchController(BranchService branchService, MetamodelProvider metamodelProvider) {
        this.branchService = branchService;
        this.metamodelProvider = metamodelProvider;
    }

    public Result createWithProjectId(UUID projectId, Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Branch requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Branch.class));
        if (requestedObject.getId() != null || requestedObject.getTimestamp() != null) {
            return Results.badRequest();
        }
        requestedObject.setTimestamp(ZonedDateTime.now());
        Optional<Branch> response = branchService.create(projectId, requestedObject);
        return response.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }

    public Result byProject(UUID projectId, Http.Request request) {
        PageRequest pageRequest = PageRequest.from(request);
        List<Branch> branches = branchService.getByProjectId(
                projectId,
                pageRequest.getAfter(),
                pageRequest.getBefore(),
                pageRequest.getSize()
        );
        return Optional.of(branches)
                .map(collection -> JacksonHelper.collectionToTree(
                        collection,
                        List.class,
                        metamodelProvider.getImplementationClass(Branch.class)
                ))
                .map(Results::ok)
                .map(result -> paginateResult(
                        result,
                        branches.size(),
                        idx -> branches.get(idx).getId(),
                        request,
                        pageRequest
                ))
                .orElseThrow();
    }

    public Result byProjectAndId(UUID projectId, UUID branchId) {
        Optional<Branch> branch = branchService.getByProjectIdAndId(projectId, branchId);
        return branch.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }
}
