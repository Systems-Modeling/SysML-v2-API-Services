package dao;

import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RelationshipDao extends Dao<Relationship> {
    Optional<Relationship> persist(Relationship relationship);

    Optional<Relationship> update(Relationship relationship);

    Optional<Relationship> findById(UUID id);

    List<Relationship> findAll();

    void delete(Relationship relationship);

    void deleteAll();

    Set<Relationship> findAllByCommitRelatedElement(Commit commit, Element relatedElement);
}
