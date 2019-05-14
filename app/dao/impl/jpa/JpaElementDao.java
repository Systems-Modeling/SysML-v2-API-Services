package dao.impl.jpa;

import dao.ElementDao;
import jpa.manager.JPAManager;
import models.Element;
import models.Element_;
import models.Model;

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
public class JpaElementDao extends JpaDao<Element> implements ElementDao {
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
            CriteriaQuery<Element> query = builder.createQuery(Element.class);
            Root<Element> root = query.from(Element.class);
            query.select(root).where(builder.equal(root.get(Element_.id), id));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Element> findAll() {
        return jpa.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Element> query = cb.createQuery(Element.class);
            Root<Element> root = query.from(Element.class);
            query.select(root);
            return em.createQuery(query).getResultList();
        });
    }

    @Override
    public void deleteAll() {
        jpa.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaDelete<Element> query = cb.createCriteriaDelete(Element.class);
            return em.createQuery(query).getResultList();
        });
    }

    @Override
    public List<Element> findAllByModel(Model model) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Element> query = builder.createQuery(Element.class);
            Root<Element> root = query.from(Element.class);
            query.select(root).where(builder.equal(root.get(Element_.model), model));
            return em.createQuery(query).getResultList();
        });
    }

    @Override
    public Optional<Element> findByModelAndId(Model model, UUID id) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Element> query = builder.createQuery(Element.class);
            Root<Element> root = query.from(Element.class);
            query.select(root).where(builder.equal(root.get(Element_.model), model)).where(builder.equal(root.get(Element_.id), id));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }
}
