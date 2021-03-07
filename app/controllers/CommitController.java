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
import com.fasterxml.jackson.databind.ObjectMapper;
import config.MetamodelProvider;
import jackson.JacksonHelper;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.CommitService;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CommitController extends BaseController {

    private final MetamodelProvider metamodelProvider;
    private final CommitService commitService;

    @Inject
    public CommitController(CommitService commitService, MetamodelProvider metamodelProvider) {
        this.commitService = commitService;
        this.metamodelProvider = metamodelProvider;
    }

    public Result createWithProjectId(UUID projectId, Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Commit requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Commit.class));
        if (requestedObject.getId() != null || requestedObject.getTimestamp() != null) {
            return Results.badRequest();
        }
        requestedObject.setTimestamp(ZonedDateTime.now());
        Optional<Commit> response = commitService.create(projectId, requestedObject);
        return response.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }

    public Result byProject(UUID projectId, Http.Request request) {
        PageRequest pageRequest = PageRequest.from(request);
        List<Commit> commits = commitService.getByProjectId(
                projectId,
                pageRequest.getAfter(),
                pageRequest.getBefore(),
                pageRequest.getSize()
        );
        return Optional.of(commits)
                .map(collection -> JacksonHelper.collectionToTree(
                        collection,
                        List.class,
                        metamodelProvider.getImplementationClass(Commit.class),
                        Json::mapper,
                        writer -> writer.withView(CommitImpl.Views.Compact.class)))
                .map(Results::ok)
                .map(result -> paginateResult(
                        result,
                        commits.size(),
                        idx -> commits.get(idx).getId(),
                        request,
                        pageRequest
                ))
                .orElseThrow();
    }

    public Result byProjectAndId(UUID projectId, UUID commitId) {
        Optional<Commit> commit = commitService.getByProjectIdAndId(projectId, commitId);
        return commit.map(e -> ok(Json.toJson(e))).orElseGet(Results::notFound);
    }

    public Result headByProject(UUID projectId) {
        Optional<Commit> commit = commitService.getHeadByProjectId(projectId);
        return commit.map(e -> ok(JacksonHelper.objectToTree(commit, Json::mapper, mapper -> mapper.writer().withView(CommitImpl.Views.Compact.class), ObjectMapper::reader)))
                .orElseGet(Results::notFound);
    }
}
