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

package services;

import dao.CommitDao;
import dao.ElementDao;
import dao.ProjectDao;
import dao.QueryDao;
import jackson.filter.AllowedPropertyFilter;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.query.Query;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Singleton
public class QueryService extends BaseService<Query, QueryDao> {

    private final ProjectDao projectDao;
    private final CommitDao commitDao;
    private final ElementDao elementDao;

    @Inject
    public QueryService(QueryDao queryDao, ProjectDao projectDao, CommitDao commitDao, ElementDao elementDao) {
        super(queryDao);
        this.projectDao = projectDao;
        this.commitDao = commitDao;
        this.elementDao = elementDao;
    }

    public Optional<Query> create(Query query) {
        return query.getId() != null ? update(query) : persist(query);
    }

    public Optional<Query> create(UUID projectId, Query query) {
        query.setOwningProject(projectDao.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project " + projectId + " not found.")));
        return create(query);
    }

    public List<Query> getByProjectId(UUID projectId, @Nullable UUID after, @Nullable UUID before, int maxResults) {
        return projectDao.findById(projectId)
                .map(project -> dao.findAllByProject(project, after, before, maxResults))
                .orElse(Collections.emptyList());
    }

    public Optional<Query> getByProjectIdAndId(UUID projectId, UUID queryId) {
        return projectDao.findById(projectId).flatMap(project -> dao.findByProjectAndId(project, queryId));
    }

    public QueryResults getQueryResultsByProjectIdQueryId(UUID projectId, UUID queryId, @Nullable UUID commitId) {
        return getQueryResults(projectId, project -> dao.findByProjectAndId(project, queryId).orElseThrow(() -> new IllegalArgumentException("Query " + queryId + " not found.")), commitId);
    }

    public QueryResults getQueryResultsByProjectIdQuery(UUID projectId, Query query, @Nullable UUID commitId) {
        return getQueryResults(projectId, project -> query, commitId);
    }

    private QueryResults getQueryResults(UUID projectId, Function<Project, Query> queryFunction, @Nullable UUID commitId) {
        Project project = projectDao.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project " + projectId + " not found."));

        Commit commit = commitId != null ?
                commitDao.findByProjectAndId(project, commitId).orElseThrow(() -> new IllegalArgumentException("Commit " + commitId + " not found.")) :
                commitDao.findHeadByProject(project).orElseThrow(() -> new IllegalStateException("Project " + projectId + " has no commits."));

        Query query = queryFunction.apply(project);
        AllowedPropertyFilter propertyFilter = getPropertyFilter(query);
        return new QueryResults(elementDao.findByCommitAndQuery(commit, query), commit, propertyFilter);
    }

    public static class QueryResults {
        private final List<Element> elements;
        private final Commit commit;
        private final AllowedPropertyFilter propertyFilter;

        public QueryResults(List<Element> elements, Commit commit, AllowedPropertyFilter propertyFilter) {
            this.elements = elements;
            this.commit = commit;
            this.propertyFilter = propertyFilter;
        }

        public List<Element> getElements() {
            return elements;
        }

        public Commit getCommit() {
            return commit;
        }

        public AllowedPropertyFilter getPropertyFilter() {
            return propertyFilter;
        }
    }

    private AllowedPropertyFilter getPropertyFilter(Query query) {
        if (query.getSelect() == null || query.getSelect().isEmpty()) {
            return null;
        }
        return new AllowedPropertyFilter(query.getSelect());
    }

}
