package services;

import dao.ElementDao;
import dao.ProjectDao;
import org.omg.sysml.metamodel.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class ElementService {
    @Inject
    private ElementDao elementDao;

    @Inject
    private ProjectDao projectDao;

    public List<Element> getAll() {
        return elementDao.findAll();
    }

    public Optional<Element> getById(UUID id) {
        return elementDao.findById(id);
    }

    public List<Element> getByProjectId(UUID projectId) {
        return projectDao.findById(projectId).map(m -> elementDao.findAllByProject(m)).orElse(Collections.emptyList());
    }

    public Optional<Element> getByProjectIdAndId(UUID projectId, UUID elementId) {
        return projectDao.findById(projectId).flatMap(m -> elementDao.findByProjectAndId(m, elementId));
    }

    public Optional<Element> create(Element element) {
        return element.getIdentifier() != null ? elementDao.update(element) : elementDao.persist(element);
    }
}
