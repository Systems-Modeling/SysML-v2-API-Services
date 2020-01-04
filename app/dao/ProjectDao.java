package dao;

import org.omg.sysml.lifecycle.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectDao extends Dao<Project> {
    Optional<Project> persist(Project project);

    Optional<Project> update(Project project);

    Optional<Project> findById(UUID id);

    List<Project> findAll();

    void delete(Project project);

    void deleteAll();
}
