package dao.impl.jpa;

import dao.Dao;
import jpa.manager.JPAManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

public abstract class JpaDao<E> implements Dao<E> {

    protected abstract JPAManager getJpaManager();

    @Override
    public Optional<E> update(E e) {
        return Optional.ofNullable(getJpaManager().transact(em -> {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(e);
            transaction.commit();
            return e;
        }));
    }

    @Override
    public Optional<E> persist(E e) {
        return getJpaManager().transact(em -> {
            return persist(e, em);
        });
    }

    protected Optional<E> persist(E e, EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(e);
        transaction.commit();
        return Optional.ofNullable(e);
    }

    @Override
    public void delete(E e) {
        getJpaManager().transact(em -> {
            em.remove(e);
        });
    }
}
