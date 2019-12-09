package services;

import dao.CommitDao;
import dao.ProjectDao;
import org.omg.sysml.versioning.Commit;

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
        return projectDao.findById(projectId).map(m -> commitDao.findAllByProject(m)).orElse(Collections.emptyList());
    }

    public Optional<Commit> getByProjectIdAndId(UUID projectId, UUID commitId) {
        return projectDao.findById(projectId).flatMap(m -> commitDao.findByProjectAndId(m, commitId));
    }

    public Optional<Commit> create(Commit commit) {
        return commit.getId() != null ? commitDao.update(commit) : commitDao.persist(commit);
    }
}
