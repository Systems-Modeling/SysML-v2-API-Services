package dao.impl.jpa;

import dao.ModelDao;
import jpa.manager.JPAManager;
import models.Model;
import models.Model_;

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
public class JpaModelDao extends JpaDao<Model> implements ModelDao {
    @Inject
    private JPAManager jpa;

    @Override
    protected JPAManager getJpaManager() {
        return jpa;
    }

    @Override
    public Optional<Model> findById(UUID id) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Model> query = builder.createQuery(Model.class);
            Root<Model> root = query.from(Model.class);
            query.select(root).where(builder.equal(root.get(Model_.id), id));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Model> findAll() {
        return jpa.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Model> query = cb.createQuery(Model.class);
            Root<Model> root = query.from(Model.class);
            query.select(root);
            return em.createQuery(query).getResultList();
        });
    }

    @Override
    public void deleteAll() {
        jpa.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaDelete<Model> query = cb.createCriteriaDelete(Model.class);
            return em.createQuery(query).getResultList();
        });
    }
}
