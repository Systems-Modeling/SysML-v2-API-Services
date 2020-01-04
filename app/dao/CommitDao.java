package dao;

import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.Commit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommitDao extends Dao<Commit> {
    Optional<Commit> persist(Commit commit);

    Optional<Commit> update(Commit commit);

    Optional<Commit> findById(UUID id);

    List<Commit> findAll();

    void delete(Commit commit);

    void deleteAll();

    List<Commit> findAllByProject(Project project);

    Optional<Commit> findByProjectAndId(Project project, UUID id);
}
