/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020 InterCAX LLC
 * Copyright (C) 2020 California Institute of Technology ("Caltech")
 * Copyright (C) 2021 Twingineer LLC
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
import jackson.jsonld.JsonLdAdorner;
import jackson.jsonld.RecordAdorners.BranchAdorner;
import jackson.jsonld.RecordAdorners.ProjectContainmentParameters;
import org.omg.sysml.lifecycle.Branch;
import play.Environment;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
import services.BranchService;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BranchController extends JsonLdController<Branch, ProjectContainmentParameters> {

    private final MetamodelProvider metamodelProvider;
    private final BranchService branchService;
    private final JsonLdAdorner<Branch, ProjectContainmentParameters> adorner;

    @Inject
    public BranchController(BranchService branchService, MetamodelProvider metamodelProvider, Environment environment) {
        this.branchService = branchService;
        this.metamodelProvider = metamodelProvider;
        this.adorner = new BranchAdorner(environment, INLINE_JSON_LD_CONTEXT);
    }

    public Result postBranchByProject(UUID projectId, Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Branch requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Branch.class));
        if (requestedObject.getId() != null || requestedObject.getCreated() != null) {
            return Results.badRequest();
        }
        requestedObject.setCreated(ZonedDateTime.now());
        Optional<Branch> branch = branchService.create(projectId, requestedObject);
        if (branch.isEmpty()) {
            return Results.internalServerError();
        }
        return buildResult(branch.get(), request, new ProjectContainmentParameters(projectId));
    }

    public Result getBranchesByProject(UUID projectId, Request request) {
        PageRequest<UUID> pageRequest = uuidRequest(request);
        List<Branch> branches = branchService.getByProjectId(
                projectId,
                pageRequest.getAfter(),
                pageRequest.getBefore(),
                pageRequest.getSize()
        );
        return uuidResponse(
                buildResult(branches, List.class, metamodelProvider.getImplementationClass(Branch.class), request, new ProjectContainmentParameters(projectId)),
                branches.size(),
                idx -> branches.get(idx).getId(),
                request,
                pageRequest
        );
    }

    public Result getBranchByProjectAndId(UUID projectId, UUID branchId, Request request) {
        Optional<Branch> branch = branchService.getByProjectIdAndId(projectId, branchId);
        return buildResult(branch.orElse(null), request, new ProjectContainmentParameters(projectId));
    }

    public Result deleteBranchByProjectAndId(UUID projectId, UUID branchId, Request request) {
        Optional<Branch> branch = branchService.deleteByProjectIdAndId(projectId, branchId);
        return buildResult(branch.orElse(null), request, new ProjectContainmentParameters(projectId));
    }

    @Override
    protected JsonLdAdorner<Branch, ProjectContainmentParameters> getAdorner() {
        return adorner;
    }
}
