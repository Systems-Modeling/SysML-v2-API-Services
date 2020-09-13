package services;

import dao.ProjectDao;
import dao.QueryDao;
import org.omg.sysml.query.Query;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class QueryService extends BaseService<Query, QueryDao> {

    private final ProjectDao projectDao;

    @Inject
    public QueryService(QueryDao dao, ProjectDao projectDao) {
        super(dao);
        this.projectDao = projectDao;
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
}
