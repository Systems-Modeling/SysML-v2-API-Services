package dao.impl.jpa;

import dao.ProjectDao;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.impl.ProjectImpl;
import org.omg.sysml.lifecycle.impl.ProjectImpl_;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JpaProjectDao extends SimpleJpaDao<Project, ProjectImpl> implements ProjectDao {

    @Inject
    public JpaProjectDao(JPAManager jpaManager) {
        super(jpaManager, ProjectImpl.class, ProjectImpl_.id);
    }
}
