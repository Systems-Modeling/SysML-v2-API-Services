package services;

import dao.ProjectDao;
import org.omg.sysml.lifecycle.Project;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ProjectService extends BaseService<Project, ProjectDao> {

    @Inject
    public ProjectService(ProjectDao dao) {
        super(dao);
    }

    public Optional<Project> create(Project project) {
        return project.getId() != null ? dao.update(project) : dao.persist(project);
    }
}
