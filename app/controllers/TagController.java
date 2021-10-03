/*
 * SysML v2 REST/HTTP Pilot Implementation
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
import jackson.jsonld.RecordAdorners.ProjectContainmentParameters;
import jackson.jsonld.RecordAdorners.TagAdorner;
import org.omg.sysml.lifecycle.Tag;
import play.Environment;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
import services.TagService;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TagController extends JsonLdController<Tag, ProjectContainmentParameters> {

    private final MetamodelProvider metamodelProvider;
    private final TagService tagService;
    private final JsonLdAdorner<Tag, ProjectContainmentParameters> adorner;

    @Inject
    public TagController(TagService tagService, MetamodelProvider metamodelProvider, Environment environment) {
        this.tagService = tagService;
        this.metamodelProvider = metamodelProvider;
        this.adorner = new TagAdorner(environment, INLINE_JSON_LD_CONTEXT);
    }

    public Result postTagByProject(UUID projectId, Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Tag requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Tag.class));
        if (requestedObject.getId() != null || requestedObject.getTimestamp() != null) {
            return Results.badRequest();
        }
        requestedObject.setTimestamp(ZonedDateTime.now());
        Optional<Tag> tag = tagService.create(projectId, requestedObject);
        if (tag.isEmpty()) {
            return Results.internalServerError();
        }
        return buildResult(tag.get(), request, new ProjectContainmentParameters(projectId));
    }

    public Result getTagsByProject(UUID projectId, Request request) {
        PageRequest pageRequest = PageRequest.from(request);
        List<Tag> tags = tagService.getByProjectId(
                projectId,
                pageRequest.getAfter(),
                pageRequest.getBefore(),
                pageRequest.getSize()
        );
        return paginateResult(
                buildResult(tags, List.class, metamodelProvider.getImplementationClass(Tag.class), request, new ProjectContainmentParameters(projectId)),
                tags.size(),
                idx -> tags.get(idx).getId(),
                request,
                pageRequest
        );
    }

    public Result getTagByProjectAndId(UUID projectId, UUID tagId, Request request) {
        Optional<Tag> tag = tagService.getByProjectIdAndId(projectId, tagId);
        return buildResult(tag.orElse(null), request, new ProjectContainmentParameters(projectId));
    }

    public Result deleteTagByProjectAndId(UUID projectId, UUID tagId, Request request) {
        Optional<Tag> tag = tagService.deleteByProjectIdAndId(projectId, tagId);
        return buildResult(tag.orElse(null), request, new ProjectContainmentParameters(projectId));
    }

    @Override
    protected JsonLdAdorner<Tag, ProjectContainmentParameters> getAdorner() {
        return adorner;
    }
}
