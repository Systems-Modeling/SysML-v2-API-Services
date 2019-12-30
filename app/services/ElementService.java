package services;

import dao.CommitDao;
import dao.ElementDao;
import dao.ProjectDao;
import org.omg.sysml.metamodel.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

@Singleton
public class ElementService {
    @Inject
    private ElementDao elementDao;

    @Inject
    private ProjectDao projectDao;

    @Inject
    private CommitDao commitDao;

    public List<Element> getAll() {
        return elementDao.findAll();
    }

    public Optional<Element> getById(UUID id) {
        return elementDao.findById(id);
    }

    public List<Element> getByProjectId(UUID projectId) {
        return projectDao.findById(projectId).map(m -> elementDao.findAllByProject(m)).orElse(Collections.emptyList());
    }

    public Set<Element> getByCommitId(UUID commitId) {
        return commitDao.findById(commitId).map(c -> elementDao.findAllByCommit(c)).orElse(Collections.emptySet());
    }

    public Optional<Element> getByProjectIdAndId(UUID projectId, UUID elementId) {
        return projectDao.findById(projectId).flatMap(m -> elementDao.findByProjectAndId(m, elementId));
    }

    public Optional<Element> getByCommitIdAndId(UUID commitId, UUID elementId) {
        return commitDao.findById(commitId).flatMap(m -> elementDao.findByCommitAndId(m, elementId));
    }

    public Optional<Element> create(Element element) {
        return element.getIdentifier() != null ? elementDao.update(element) : elementDao.persist(element);
    }
}
