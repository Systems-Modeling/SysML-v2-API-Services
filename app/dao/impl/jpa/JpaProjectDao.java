package dao.impl.jpa;

import dao.ProjectDao;
import jpa.manager.JPAManager;
import org.omg.sysml.extension.Project;
import org.omg.sysml.extension.impl.ProjectImpl;
import org.omg.sysml.extension.impl.ProjectImpl_;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class JpaProjectDao extends JpaDao<Project> implements ProjectDao {
    @Inject
    private JPAManager jpa;

    @Override
    protected JPAManager getJpaManager() {
        return jpa;
    }

    @Override
    public Optional<Project> findById(UUID id) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<ProjectImpl> query = builder.createQuery(ProjectImpl.class);
            Root<ProjectImpl> root = query.from(ProjectImpl.class);
            query.select(root).where(builder.equal(root.get(ProjectImpl_.id), id));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Project> findAll() {
        return jpa.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Project> query = cb.createQuery(Project.class);
            Root<ProjectImpl> root = query.from(ProjectImpl.class);
            query.select(root);
            return em.createQuery(query).getResultList();
        });
    }

    @Override
    public void deleteAll() {
        jpa.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaDelete<Project> query = cb.createCriteriaDelete(Project.class);
            return em.createQuery(query).getResultList();
        });
    }
}
