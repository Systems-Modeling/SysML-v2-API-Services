package services;

import dao.RelationshipDAO;
import models.Relationship;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Singleton
public class RelationshipService {
    @Inject
    private RelationshipDAO dao;

    public Relationship create(Relationship relationship) {
        return dao.create(relationship);
    }

    public void delete(UUID modelId, UUID relationshipId) {
        dao.delete(modelId, relationshipId);
    }

    public void deleteAll(UUID modelId) {
        dao.deleteAll(modelId);
    }

    public List<Relationship> getAll(UUID modelId) {
        return dao.getAll(modelId);
    }

    public Relationship getById(UUID modelId, UUID relationshipId) {
        return dao.getById(modelId, relationshipId);
    }

    public List<Relationship> getBySourceId(UUID modelId, UUID elementId) {
        return dao.getBySourceId(modelId, elementId);
    }

    public List<Relationship> getByTargetId(UUID modelId, UUID elementId) {
        return dao.getByTargetId(modelId, elementId);
    }

    public Relationship update(Relationship relationship) {
        return dao.update(relationship);
    }

    public List<Relationship> updateAll(UUID modelId, Collection<Relationship> deserialized) {
        return dao.updateAll(modelId, deserialized);
    }
}
