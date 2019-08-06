package dao;

import org.omg.sysml.extension.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectDao extends Dao<Project> {
    Optional<Project> persist(Project Project);

    Optional<Project> update(Project entity);

    Optional<Project> findById(UUID id);

    List<Project> findAll();

    void delete(Project Project);

    void deleteAll();
}
