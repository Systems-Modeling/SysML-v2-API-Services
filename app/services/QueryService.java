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
import java.util.*;
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
        query.setContainingProject(projectDao.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project " + projectId + " not found.")));
        return create(query);
    }

    public List<Query> getByProjectId(UUID projectId) {
        return projectDao.findById(projectId).map(dao::findAllByProject).orElse(Collections.emptyList());
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
        private final Set<Element> elements;
        private final Commit commit;
        private final AllowedPropertyFilter propertyFilter;

        public QueryResults(Set<Element> elements, Commit commit, AllowedPropertyFilter propertyFilter) {
            this.elements = elements;
            this.commit = commit;
            this.propertyFilter = propertyFilter;
        }

        public Set<Element> getElements() {
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
