package services;


import dao.ElementDao;
import dao.ProjectDao;
import dao.RelationshipDao;
import org.omg.sysml.metamodel.Relationship;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

@Singleton
public class RelationshipService {
    @Inject
    private RelationshipDao relationshipDao;

    @Inject
    private ElementDao elementDao;

    @Inject
    private ProjectDao projectDao;

    public List<Relationship> getAll() {
        return relationshipDao.findAll();
    }

    public Optional<Relationship> getById(UUID id) {
        return relationshipDao.findById(id);
    }

    public Optional<Relationship> create(Relationship relationship) {
        return relationship.getIdentifier() != null ? relationshipDao.update(relationship) : relationshipDao.persist(relationship);
    }

    public List<Relationship> getByRelatedElementId(UUID elementId) {
        return elementDao.findById(elementId).map(e -> relationshipDao.findAllByRelatedElement(e)).orElse(Collections.emptyList());
    }

    public List<Relationship> getBySourceElementId(UUID elementId) {
        return elementDao.findById(elementId).map(e -> relationshipDao.findAllBySourceElement(e)).orElse(Collections.emptyList());
    }

    public List<Relationship> getByTargetElementId(UUID elementId) {
        return elementDao.findById(elementId).map(e -> relationshipDao.findAllByTargetElement(e)).orElse(Collections.emptyList());
    }

    public List<Relationship> getByProjectId(UUID projectId) {
        return projectDao.findById(projectId).map(m -> relationshipDao.findAllByProject(m)).orElse(Collections.emptyList());
    }
}
