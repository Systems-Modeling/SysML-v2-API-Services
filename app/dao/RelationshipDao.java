package dao;

import models.Element;
import models.Relationship;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RelationshipDao extends Dao<Relationship> {
    Optional<Relationship> persist(Relationship Relationship);

    Optional<Relationship> update(Relationship entity);

    Optional<Relationship> findById(UUID id);

    List<Relationship> findAll();

    void delete(Relationship Relationship);

    void deleteAll();

    List<Relationship> findAllByRelatedElement(Element element);

    List<Relationship> findAllBySourceElement(Element element);

    List<Relationship> findAllByTargetElement(Element element);
}
