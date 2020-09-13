package dao;

import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;

import java.util.Set;

public interface RelationshipDao extends Dao<Relationship> {

    Set<Relationship> findAllByCommitRelatedElement(Commit commit, Element relatedElement);
}
