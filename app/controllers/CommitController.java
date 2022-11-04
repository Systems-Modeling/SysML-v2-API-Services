/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020 InterCAX LLC
 * Copyright (C) 2020 California Institute of Technology ("Caltech")
 * Copyright (C) 2022 Twingineer LLC
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
import jackson.jsonld.JsonLdAdorner;
import jackson.jsonld.RecordAdorners.CommitAdorner;
import jackson.jsonld.RecordAdorners.ProjectContainmentParameters;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.DataVersion;
import play.Environment;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
import services.CommitService;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CommitController extends JsonLdController<Commit, ProjectContainmentParameters> {

    private final MetamodelProvider metamodelProvider;
    private final CommitService commitService;
    private final JsonLdAdorner<Commit, ProjectContainmentParameters> adorner;

    @Inject
    public CommitController(CommitService commitService, MetamodelProvider metamodelProvider, Environment environment) {
        this.commitService = commitService;
        this.metamodelProvider = metamodelProvider;
        this.adorner = new CommitAdorner(environment, INLINE_JSON_LD_CONTEXT);
    }

    public Result postCommitByProject(UUID projectId, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<UUID> branchId, Request request) {
        return postCommitByProject(projectId, branchId, request, commitService::create);
    }

    public Result postCommitByProjectNameResolved(UUID projectId, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<UUID> branchId, Request request) {
        return postCommitByProject(projectId, branchId, request, commitService::createNameResolved);
    }

    private Result postCommitByProject(UUID projectId, @SuppressWarnings("OptionalUsedAsFieldOrParameterType") Optional<UUID> branchId, Request request, CommitCreator creator) {
        JsonNode requestBodyJson = request.body().asJson();
        Commit requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Commit.class));
        if (requestedObject.getId() != null || requestedObject.getCreated() != null) {
            return Results.badRequest();
        }
        requestedObject.setCreated(ZonedDateTime.now());
        Optional<Commit> commit = creator.create(projectId, branchId.orElse(null), requestedObject);
        if (commit.isEmpty()) {
            return Results.internalServerError();
        }
        boolean ld = respondWithJsonLd(request);
        JsonNode json = buildJson(
                commit.get(),
                request,
                new ProjectContainmentParameters(projectId),
                ld,
                Json.mapper()
        );
        return buildResult(json, ld);
    }

    public Result getCommitsByProject(UUID projectId, Request request) {
        PageRequest<UUID> pageRequest = uuidRequest(request);
        List<Commit> commits = commitService.getByProjectId(
                projectId,
                pageRequest.getAfter(),
                pageRequest.getBefore(),
                pageRequest.getSize()
        );
        boolean ld = respondWithJsonLd(request);
        JsonNode json = buildJson(
                commits,
                List.class,
                metamodelProvider.getImplementationClass(Commit.class),
                request,
                new ProjectContainmentParameters(projectId),
                ld,
                Json.mapper()
        );
        Result result = buildResult(json, ld);
        return uuidResponse(
                result,
                commits.size(),
                idx -> commits.get(idx).getId(),
                request,
                pageRequest
        );
    }

    public Result getCommitByProjectAndId(UUID projectId, UUID commitId, Request request) {
        if (respondWithJsonLd(request)) {
            // TODO implement
            return Results.status(NOT_IMPLEMENTED);
        }
        Optional<Commit> commit = commitService.getByProjectIdAndId(projectId, commitId);
        return buildResult(commit.orElse(null), request, new ProjectContainmentParameters(projectId));
    }

    public Result getChangesByProjectAndCommit(UUID projectId, UUID commitId, Request request) {
        if (respondWithJsonLd(request)) {
            // TODO implement
            return Results.status(NOT_IMPLEMENTED);
        }
        PageRequest<UUID> pageRequest = uuidRequest(request);
        List<DataVersion> changes = commitService.getChangesByProjectIdAndCommitId(
                projectId,
                commitId,
                pageRequest.getAfter(),
                pageRequest.getBefore(),
                pageRequest.getSize()
        );
        boolean ld = respondWithJsonLd(request);
        JsonNode json = JacksonHelper.collectionToTree(
                changes,
                List.class,
                metamodelProvider.getImplementationClass(DataVersion.class),
                Json.mapper()
        );
        Result result = buildResult(json, ld);
        return uuidResponse(
                result,
                changes.size(),
                idx -> changes.get(idx).getId(),
                request,
                pageRequest
        );
    }

    public Result getChangeByProjectCommitAndId(UUID projectId, UUID commitId, UUID changeId, Request request) {
        if (respondWithJsonLd(request)) {
            // TODO implement
            return Results.status(NOT_IMPLEMENTED);
        }
        Optional<DataVersion> change = commitService.getChangeByProjectIdCommitIdAndId(projectId, commitId, changeId);
        JsonNode json = JacksonHelper.objectToTree(
                change,
                Json.mapper()
        );
        return Results.ok(json);
    }

    @Override
    protected JsonLdAdorner<Commit, ProjectContainmentParameters> getAdorner() {
        return adorner;
    }

    @FunctionalInterface
    private interface CommitCreator {
        Optional<Commit> create(UUID projectId, UUID branchId, Commit commit);
    }
}
