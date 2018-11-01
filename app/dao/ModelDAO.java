package dao;

import kundera.Dialects;
import kundera.JPAManager;
import models.Element;
import models.Model;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Main service that provides CRUD operations for all SysML v2 models
 */

@Singleton
public class ModelDAO {
    @Inject
    private JPAManager kundera;

    public Model create(Model model) {
        if (model != null) {
            kundera.transact(entityManager -> {
                entityManager.persist(model);
                entityManager.flush();
            });
            return getById(model.getId());
        }
        return null;
    }

    public void delete(UUID modelId) {
        kundera.transact(em -> {
            Query query;
            String queryString;
            if (Dialects.isDialectCassandra(em.getEntityManagerFactory(), kundera.getPersistenceUnitName())) {
                /*
                TODO
                The following query *MUST* be sanitized before being placed in production code.
                The preferred way would be to use bind parameters, however as of Kundera 3.13, they are not properly supported by Kundera in our use case.
                See https://github.com/Impetus/Kundera/issues/1029
                */
                //The schema is auto-generated with the id column named key
                queryString = "DELETE FROM models WHERE key = " + modelId;
                query = em.createNativeQuery(queryString);
            }
            else {
                queryString = "DELETE FROM Model m WHERE m.id = :modelId";
                query = em.createQuery(queryString);
                query.setParameter("modelId", modelId);
            }
            query.executeUpdate();
        });
    }

    public void deleteAll() {
        kundera.transact((Consumer<EntityManager>) this::deleteAll);
    }

    public Model getById(UUID modelId) {
        return kundera.transact(em -> {
            Query query;
            String queryString;
            if (Dialects.isDialectCassandra(em.getEntityManagerFactory(), kundera.getPersistenceUnitName())) {
                /*
                TODO
                The following query *MUST* be sanitized before being placed in production code.
                The preferred way would be to use bind parameters, however as of Kundera 3.13, they are not properly supported by Kundera in our use case.
                See https://github.com/Impetus/Kundera/issues/1029
                */
                //The schema is auto-generated with the id column named key
                queryString = "SELECT * FROM models WHERE key = " + modelId;
                query = em.createNativeQuery(queryString, Model.class);
            }
            else {
                queryString = "SELECT m FROM Model m WHERE m.id = :modelId";
                query = em.createQuery(queryString, Model.class);
                query.setParameter("modelId", modelId);
            }
            List<Model> results = query.getResultList();
            if (results.size() == 0) {
                return null;
            }
            else if (results.size() > 1) {
                throw new IllegalStateException("The query attempted to return more than one Model with the specified id.");
            }
            return results.get(0);
        });
    }

    public List<Model> getAll() {
        return kundera.transact((Function<EntityManager, List<Model>>) em -> em.createQuery("SELECT m FROM Model m", Model.class).getResultList());
    }

    public Model update(Model model) {
        return kundera.transact(em -> {
            em.merge(model);
            em.flush();
            return getById(model.getId());
        });
    }

    public List<Model> updateAll(Collection<Model> deserialized) {
        return kundera.transact(em -> {
            deleteAll(em);
            for (Element element : deserialized) {
                em.persist(element);
            }
            return getAll();
        });
    }

    private void deleteAll(EntityManager em) {
        em.createQuery("DELETE FROM Model m").executeUpdate();
    }
}
