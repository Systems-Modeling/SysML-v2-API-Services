package services;

import dao.CommitDao;
import dao.ProjectDao;
import org.omg.sysml.lifecycle.Commit;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class CommitService extends BaseService<Commit, CommitDao> {

    private final ProjectDao projectDao;

    @Inject
    public CommitService(CommitDao commitDao, ProjectDao projectDao) {
        super(commitDao);
        this.projectDao = projectDao;
    }

    public Optional<Commit> create(Commit commit) {
        return commit.getId() != null ? dao.update(commit) : dao.persist(commit);
    }

    public Optional<Commit> create(UUID projectId, Commit commit) {
        commit.setContainingProject(projectDao.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project " + projectId + " not found.")));
        return create(commit);
    }

    public List<Commit> getByProjectId(UUID projectId) {
        return projectDao.findById(projectId).map(dao::findAllByProject).orElse(Collections.emptyList());
    }

    public Optional<Commit> getByProjectIdAndId(UUID projectId, UUID commitId) {
        return projectDao.findById(projectId).flatMap(project -> dao.findByProjectAndId(project, commitId));
    }

    public Optional<Commit> getHeadByProjectId(UUID projectId) {
        return projectDao.findById(projectId).flatMap(dao::findHeadByProject);
    }
}
