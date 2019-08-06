package dao.impl.jpa;

import config.MetamodelProvider;
import dao.ElementDao;
import jpa.manager.JPAManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.omg.sysml.extension.Project;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.impl.MofObjectImpl;
import org.omg.sysml.metamodel.impl.MofObjectImpl_;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class JpaElementDao extends JpaDao<Element> implements ElementDao {
    @Inject
    private MetamodelProvider metamodelProvider;

    @Inject
    private JPAManager jpa;

    @Override
    protected JPAManager getJpaManager() {
        return jpa;
    }

    @Override
    public Optional<Element> findById(UUID id) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<MofObjectImpl> query = builder.createQuery(MofObjectImpl.class);
            Root<MofObjectImpl> root = query.from(MofObjectImpl.class);
            query.select(root).where(builder.and(
                    builder.equal(root.get(MofObjectImpl_.identifier), id),
                    getTypeExpression(builder, root)
            ));
            try {
                return Optional.of((Element) em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Element> findAll() {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<MofObjectImpl> query = builder.createQuery(MofObjectImpl.class);
            Root<MofObjectImpl> root = query.from(MofObjectImpl.class);
            query.select(root).where(getTypeExpression(builder, root));
            return em.createQuery(query).getResultStream().map(o -> (Element) o).collect(Collectors.toList());
        });
    }

    @Override
    public void deleteAll() {
        jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaDelete<MofObjectImpl> query = builder.createCriteriaDelete(MofObjectImpl.class);
            Root<MofObjectImpl> root = query.from(MofObjectImpl.class);
            query.where(getTypeExpression(builder, root));
            return ((Stream<?>) em.createQuery(query).getResultStream()).map(o -> (Element) o).collect(Collectors.toList());
        });
    }

    @Override
    public List<Element> findAllByProject(Project project) {
        try (Session session = jpa.getEntityManagerFactory().unwrap(SessionFactory.class).openSession()) {
            Query<Element> query = session.createQuery("FROM org.omg.sysml.metamodel.Element WHERE containingProject = :project", Element.class);
            query.setParameter("project", project);
            return query.getResultList();
        }
    }

    @Override
    public Optional<Element> findByProjectAndId(Project project, UUID id) {
        try (Session session = jpa.getEntityManagerFactory().unwrap(SessionFactory.class).openSession()) {
            Query<Element> query = session.createQuery("FROM org.omg.sysml.metamodel.Element WHERE identifier = :identifier AND containingProject = :project", Element.class);
            query.setParameter("identifier", id);
            query.setParameter("project", project);
            try {
                return Optional.of(query.getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        }
    }

    private Expression<Boolean> getTypeExpression(CriteriaBuilder builder, Root<?> root) {
        return builder.or(metamodelProvider.getAllImplementationClasses().stream().filter(Element.class::isAssignableFrom).map(c -> builder.equal(root.type(), c)).toArray(Predicate[]::new));
    }
}
