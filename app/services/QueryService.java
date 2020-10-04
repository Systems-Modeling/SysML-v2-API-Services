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

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

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

    public Optional<Query> getByProjectIdAndId(UUID projectId, UUID commitId) {
        return projectDao.findById(projectId).flatMap(project -> dao.findByProjectAndId(project, commitId));
    }

    public Map.Entry<Set<Element>, AllowedPropertyFilter> getQueryResultsByProjectIdCommitIdQueryId(UUID projectId, UUID commitId, UUID queryId) {
        Project project = projectDao.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project " + projectId + " not found."));
        Commit commit = commitDao.findByProjectAndId(project, commitId).orElseThrow(() -> new IllegalArgumentException("Commit " + commitId + " not found."));
        Query query = dao.findByProjectAndId(project, queryId).orElseThrow(() -> new IllegalArgumentException("Query " + queryId + " not found."));
        return new AbstractMap.SimpleImmutableEntry<>(elementDao.query(commit, query), getPropertyFilter(query));
    }

    public Map.Entry<Set<Element>, AllowedPropertyFilter> getQueryResultsByProjectIdCommitIdQuery(UUID projectId, UUID commitId, Query query) {
        Project project = projectDao.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project " + projectId + " not found."));
        Commit commit = commitDao.findByProjectAndId(project, commitId).orElseThrow(() -> new IllegalArgumentException("Commit " + commitId + " not found."));
        return new AbstractMap.SimpleImmutableEntry<>(elementDao.query(commit, query), getPropertyFilter(query));
    }

    private AllowedPropertyFilter getPropertyFilter(Query query) {
        if (query.getSelect() == null || query.getSelect().isEmpty()) {
            return null;
        }
        return new AllowedPropertyFilter(query.getSelect());
    }

}
