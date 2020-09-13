package services;

import dao.CommitDao;
import dao.ElementDao;
import dao.ProjectDao;
import org.omg.sysml.metamodel.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Singleton
public class ElementService extends BaseService<Element, ElementDao> {

    private final ProjectDao projectDao;
    private final CommitDao commitDao;

    @Inject
    public ElementService(ElementDao elementDao, ProjectDao projectDao, CommitDao commitDao) {
        super(elementDao);
        this.projectDao = projectDao;
        this.commitDao = commitDao;
    }

    public Optional<Element> create(Element element) {
        return element.getIdentifier() != null ? dao.update(element) : dao.persist(element);
    }

    public Set<Element> getByCommitId(UUID commitId) {
        return commitDao.findById(commitId)
                .map(dao::findAllByCommit).orElse(Collections.emptySet());
    }

    public Optional<Element> getByCommitIdAndId(UUID commitId, UUID elementId) {
        return commitDao.findById(commitId)
                .flatMap(m -> dao.findByCommitAndId(m, elementId));
    }

    public Set<Element> getElementsByProjectIdCommitId(UUID projectId, UUID commitId) {
        return projectDao.findById(projectId)
                .flatMap(project -> commitDao.findByProjectAndId(project, commitId))
                .map(dao::findAllByCommit)
                .orElse(Collections.emptySet());
    }

    public Optional<Element> getElementsByProjectIdCommitIdElementId(UUID projectId, UUID commitId, UUID elementId) {
        return projectDao.findById(projectId)
                .flatMap(project -> commitDao.findByProjectAndId(project, commitId))
                .flatMap(commit -> dao.findByCommitAndId(commit, elementId));
    }

    public Set<Element> getRootsByProjectIdCommitId(UUID projectId, UUID commitId) {
        return projectDao.findById(projectId)
                .flatMap(project -> commitDao.findByProjectAndId(project, commitId))
                .map(dao::findRootsByCommit)
                .orElse(Collections.emptySet());
    }
}
