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
public class CommitService {
    @Inject
    private CommitDao commitDao;

    @Inject
    private ProjectDao projectDao;

    public List<Commit> getAll() {
        return commitDao.findAll();
    }

    public Optional<Commit> getById(UUID id) {
        return commitDao.findById(id);
    }

    public List<Commit> getByProjectId(UUID projectId) {
        return projectDao.findById(projectId).map(commitDao::findAllByProject).orElse(Collections.emptyList());
    }

    public Optional<Commit> getByProjectIdAndId(UUID projectId, UUID commitId) {
        return projectDao.findById(projectId).flatMap(project -> commitDao.findByProjectAndId(project, commitId));
    }

    public Optional<Commit> getHeadByProjectId(UUID projectId) {
        return projectDao.findById(projectId).flatMap(commitDao::findHeadByProject);
    }

    public Optional<Commit> create(Commit commit) {
        return commit.getId() != null ? commitDao.update(commit) : commitDao.persist(commit);
    }

    public Optional<Commit> create(UUID projectId, Commit commit) {
        commit.setContainingProject(projectDao.findById(projectId).orElseThrow(() -> new IllegalArgumentException("Project " + projectId + " not found.")));
        return create(commit);
    }
}
