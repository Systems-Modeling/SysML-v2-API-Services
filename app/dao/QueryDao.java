package dao;

import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface QueryDao extends Dao<Query> {

    List<Query> findAllByProject(Project project);

    Optional<Query> findByProjectAndId(Project project, UUID id);
}
