package services;


import dao.ElementDao;
import dao.ModelDao;
import dao.RelationshipDao;
import models.Relationship;

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
    private ModelDao modelDao;

    public List<Relationship> getAll() {
        return relationshipDao.findAll();
    }

    public Optional<Relationship> getById(UUID id) {
        return relationshipDao.findById(id);
    }

    public Optional<Relationship> create(Relationship relationship) {
        return relationship.getId() != null ? relationshipDao.update(relationship) : relationshipDao.persist(relationship);
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

    public List<Relationship> getByModelId(UUID modelId) {
        return modelDao.findById(modelId).map(m -> relationshipDao.findAllByModel(m)).orElse(Collections.emptyList());
    }
}
