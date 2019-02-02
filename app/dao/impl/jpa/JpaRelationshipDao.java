package dao.impl.jpa;

import dao.RelationshipDao;
import jpa.manager.JPAManager;
import models.Element;
import models.Relationship;
import models.Relationship_;

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
public class JpaRelationshipDao extends JpaDao<Relationship> implements RelationshipDao {
    @Inject
    private JPAManager jpa;

    @Override
    protected JPAManager getJpaManager() {
        return jpa;
    }

    @Override
    public Optional<Relationship> findById(UUID id) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Relationship> query = builder.createQuery(Relationship.class);
            Root<Relationship> root = query.from(Relationship.class);
            query.select(root).where(builder.equal(root.get(Relationship_.id), id));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Relationship> findAll() {
        return jpa.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Relationship> query = cb.createQuery(Relationship.class);
            Root<Relationship> root = query.from(Relationship.class);
            query.select(root);
            return em.createQuery(query).getResultList();
        });
    }

    @Override
    public void deleteAll() {
        jpa.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaDelete<Relationship> query = cb.createCriteriaDelete(Relationship.class);
            em.createQuery(query).getResultList();
        });
    }

    @Override
    public List<Relationship> findAllByRelatedElement(Element element) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Relationship> query = builder.createQuery(Relationship.class);
            Root<Relationship> root = query.from(Relationship.class);
            query.select(root).where(builder.or(builder.equal(root.get(Relationship_.source), element), builder.equal(root.get(Relationship_.target), element)));
            return em.createQuery(query).getResultList();
        });
    }

    @Override
    public List<Relationship> findAllBySourceElement(Element element) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Relationship> query = builder.createQuery(Relationship.class);
            Root<Relationship> root = query.from(Relationship.class);
            query.select(root).where(builder.equal(root.get(Relationship_.source), element));
            return em.createQuery(query).getResultList();
        });
    }

    @Override
    public List<Relationship> findAllByTargetElement(Element element) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Relationship> query = builder.createQuery(Relationship.class);
            Root<Relationship> root = query.from(Relationship.class);
            query.select(root).where(builder.equal(root.get(Relationship_.target), element));
            return em.createQuery(query).getResultList();
        });
    }
}
