package services;

import dao.ProjectDao;
import org.omg.sysml.extension.Project;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Manas Bajaj
 * <p>
 * Main service that provides CRUD operations for all SysML v2 projects
 */

@Singleton
public class ProjectService {
    @Inject
    private ProjectDao dao;

    public List<Project> getAll() {
        return dao.findAll();
    }

    public Optional<Project> getById(UUID id) {
        return dao.findById(id);
    }

    public Optional<Project> create(Project project) {
        return project.getId() != null ? dao.update(project) : dao.persist(project);
    }
}
