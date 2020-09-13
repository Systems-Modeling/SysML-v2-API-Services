package dao.impl.jpa;

import jpa.manager.JPAManager;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SimpleJpaDao<I, C extends I> extends JpaDao<I> {

    protected final Class<C> clazz;
    protected final SingularAttribute<? super C, UUID> idAttribute;
    protected final JPAManager jpaManager;

    protected SimpleJpaDao(JPAManager jpaManager, Class<C> clazz, SingularAttribute<? super C, UUID> idAttribute) {
        super(jpaManager);
        this.clazz = clazz;
        this.idAttribute = idAttribute;
        this.jpaManager = jpaManager;
    }

    @Override
    public Optional<I> findById(UUID id) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            @SuppressWarnings("unchecked")
            CriteriaQuery<I> query = (CriteriaQuery<I>) builder.createQuery(clazz);
            Root<C> root = query.from(clazz);
            query.select(root)
                    .where(builder.equal(root.get(idAttribute), id));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<I> findAll() {
        return jpaManager.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            @SuppressWarnings("unchecked")
            CriteriaQuery<I> query = (CriteriaQuery<I>) cb.createQuery(clazz);
            Root<C> root = query.from(clazz);
            query.select(root);
            return em.createQuery(query).getResultList();
        });
    }

    @Override
    public void deleteAll() {
        jpaManager.transact(em -> {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            @SuppressWarnings("unchecked")
            CriteriaDelete<I> query = (CriteriaDelete<I>) cb.createCriteriaDelete(clazz);
            return em.createQuery(query).getResultList();
        });
    }
}
