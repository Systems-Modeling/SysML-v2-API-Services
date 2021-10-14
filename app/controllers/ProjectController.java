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
import jackson.jsonld.RecordAdorners.ProjectAdorner;
import org.omg.sysml.lifecycle.Project;
import play.Environment;
import play.libs.Json;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
import services.ProjectService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjectController extends JsonLdController<Project, Void> {

    private final MetamodelProvider metamodelProvider;
    private final ProjectService projectService;
    private final JsonLdAdorner<Project, Void> adorner;

    @Inject
    public ProjectController(ProjectService projectService, MetamodelProvider metamodelProvider, Environment environment) {
        this.projectService = projectService;
        this.metamodelProvider = metamodelProvider;
        this.adorner = new ProjectAdorner(environment, INLINE_JSON_LD_CONTEXT);
    }

    public Result getProjectById(UUID id, Request request) {
        Optional<Project> project = projectService.getById(id);
        return buildResult(project.orElse(null), request, null);
    }

    public Result putProjectById(UUID id, Request request) {
        Optional<Project> existingProject = projectService.getById(id);
        if (existingProject.isEmpty()) {
            return Results.notFound();
        }
        JsonNode requestBodyJson = request.body().asJson();
        Project requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Project.class));
        if (requestedObject.getId() != null && !id.equals(requestedObject.getId())) {
            return Results.badRequest();
        }
        requestedObject.setId(id);
        Optional<Project> project = projectService.update(requestedObject);
        if (project.isEmpty()) {
            return Results.internalServerError();
        }
        return buildResult(project.get(), request, null);
    }

    public Result deleteProjectById(UUID id, Request request) {
        Optional<Project> project = projectService.deleteById(id);
        return buildResult(project.orElse(null), request, null);
    }

    public Result getProjects(Request request) {
        PageRequest pageRequest = PageRequest.from(request);
        List<Project> projects = projectService.getAll(pageRequest.getAfter(), pageRequest.getBefore(), pageRequest.getSize());
        return paginateResult(
                buildResult(projects, List.class, metamodelProvider.getImplementationClass(Project.class), request, null),
                projects.size(),
                idx -> projects.get(idx).getId(),
                request,
                pageRequest
        );
    }

    public Result postProject(Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Project requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Project.class));
        Optional<Project> project = projectService.create(requestedObject);
        if (project.isEmpty()) {
            return Results.internalServerError();
        }
        return buildResult(project.get(), request, null);
    }

    @Override
    protected JsonLdAdorner<Project, Void> getAdorner() {
        return adorner;
    }
}
