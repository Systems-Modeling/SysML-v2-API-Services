package dao;

import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommitDao extends Dao<Commit> {

    List<Commit> findAllByProject(Project project);

    Optional<Commit> findByProjectAndId(Project project, UUID id);

    Optional<Commit> findHeadByProject(Project project);
}
