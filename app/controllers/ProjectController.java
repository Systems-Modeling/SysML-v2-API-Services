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
import org.omg.sysml.lifecycle.Project;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import services.ProjectService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjectController extends Controller {

    private final MetamodelProvider metamodelProvider;
    private final ProjectService projectService;

    @Inject
    public ProjectController(ProjectService projectService, MetamodelProvider metamodelProvider) {
        this.projectService = projectService;
        this.metamodelProvider = metamodelProvider;
    }

    public Result byId(UUID id) {
        Optional<Project> project = projectService.getById(id);
        return project.map(m -> ok(Json.toJson(m))).orElseGet(Results::notFound);
    }

    public Result all() {
        List<Project> projects = projectService.getAll();
        return ok(JacksonHelper.collectionToTree(projects, List.class, metamodelProvider.getImplementationClass(Project.class)));
    }

    public Result create(Http.Request request) {
        JsonNode requestBodyJson = request.body().asJson();
        Project requestedObject = Json.fromJson(requestBodyJson, metamodelProvider.getImplementationClass(Project.class));
        Optional<Project> response = projectService.create(requestedObject);
        return response.map(e -> created(Json.toJson(e))).orElseGet(Results::internalServerError);
    }
}
