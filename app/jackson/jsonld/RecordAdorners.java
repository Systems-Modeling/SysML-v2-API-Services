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

package jackson.jsonld;

import com.fasterxml.jackson.databind.JsonNode;
import org.omg.sysml.lifecycle.Branch;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.impl.BranchImpl_;
import org.omg.sysml.lifecycle.impl.CommitImpl_;
import org.omg.sysml.lifecycle.impl.ProjectImpl_;
import play.Environment;
import play.mvc.Http.Request;

import java.util.UUID;
import java.util.function.UnaryOperator;

public class RecordAdorners {

    private static final String PROJECTS_PATH_SEGMENT = "projects";
    private static final String BRANCHES_PATH_SEGMENT = "branches";

    private static final String PROJECT_PATH_PREFIX = PROJECTS_PATH_SEGMENT + "/%s";

    public static class ProjectAdorner extends SimpleJsonLdAdorner<Project, Void> {

        private static final String CONTEXT_PATH = String.format("jsonld/api/%s.jsonld", Project.NAME);
        private static final String BASE_PATH = PROJECTS_PATH_SEGMENT + "/";

        public ProjectAdorner(Environment environment, boolean inline) {
            super(environment, inline);
        }

        @Override
        protected String getType(Void parameters) {
            return Project.NAME;
        }

        @Override
        protected String getContextPath(Void parameters) {
            return CONTEXT_PATH;
        }

        @Override
        protected String getBasePath(Void parameters) {
            return BASE_PATH;
        }

        @Override
        protected UnaryOperator<JsonNode> getPostProcessor(Project entity, Request request, Void parameters) {
            return constructBasePostProcessor(ProjectImpl_.DEFAULT_BRANCH, String.format(PROJECT_PATH_PREFIX, entity.getId()) + String.format("/%s/", BRANCHES_PATH_SEGMENT), request);
        }
    }

    public static class BranchAdorner extends SimpleJsonLdAdorner<Branch, ProjectContainmentParameters> {

        private static final String CONTEXT_PATH = String.format("jsonld/api/%s.jsonld", Branch.NAME);
        private static final String BASE_PATH = ProjectAdorner.BASE_PATH + "%s/branches/";

        public BranchAdorner(Environment environment, boolean inline) {
            super(environment, inline);
        }

        @Override
        protected String getType(ProjectContainmentParameters parameters) {
            return Branch.NAME;
        }

        @Override
        protected String getContextPath(ProjectContainmentParameters parameters) {
            return CONTEXT_PATH;
        }

        @Override
        protected String getBasePath(ProjectContainmentParameters parameters) {
            return String.format(BASE_PATH, parameters.getProjectId());
        }

        @Override
        protected UnaryOperator<JsonNode> getPostProcessor(Branch entity, Request request, ProjectContainmentParameters parameters) {
            return json -> constructBasePostProcessor(BranchImpl_.HEAD, String.format(CommitAdorner.BASE_PATH, parameters.getProjectId()), request).apply(
                    constructBasePostProcessor(BranchImpl_.OWNING_PROJECT, ProjectAdorner.BASE_PATH, request).apply(
                            json
                    )
            );
        }
    }

    public static class CommitAdorner extends SimpleJsonLdAdorner<Commit, ProjectContainmentParameters> {

        private static final String CONTEXT_PATH = String.format("jsonld/api/%s.jsonld", Commit.NAME);
        private static final String BASE_PATH = ProjectAdorner.BASE_PATH + "%s/commits/";

        public CommitAdorner(Environment environment, boolean inline) {
            super(environment, inline);
        }

        @Override
        protected String getType(ProjectContainmentParameters parameters) {
            return Commit.NAME;
        }

        @Override
        protected String getContextPath(ProjectContainmentParameters parameters) {
            return CONTEXT_PATH;
        }

        @Override
        protected String getBasePath(ProjectContainmentParameters parameters) {
            return String.format(BASE_PATH, parameters.getProjectId());
        }

        @Override
        protected UnaryOperator<JsonNode> getPostProcessor(Commit entity, Request request, ProjectContainmentParameters parameters) {
            return json -> constructBasePostProcessor(CommitImpl_.OWNING_PROJECT, ProjectAdorner.BASE_PATH, request).apply(
                    constructBasePostProcessor(CommitImpl_.PREVIOUS_COMMIT, getBasePath(parameters), request).apply(
                            json
                    )
            );
        }
    }

    public static class ProjectContainmentParameters {

        private final UUID projectId;

        public ProjectContainmentParameters(UUID projectId) {
            this.projectId = projectId;
        }

        public UUID getProjectId() {
            return projectId;
        }
    }
}
