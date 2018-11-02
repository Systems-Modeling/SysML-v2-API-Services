package dao;

import jpa.Dialects;
import jpa.KunderaManager;
import models.Element;
import models.Model;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

/**
 * Main service that provides CRUD operations for all SysML v2 elements
 */

@Singleton
public class ElementDAO {
    @Inject
    private KunderaManager jpa;

    public Element create(Element element) {
        if (element != null) {
            jpa.transact(em -> {
                em.persist(element);
                em.flush();
            });
            Model elementModel = element.getModel();
            if (elementModel != null) {
                return getById(elementModel.getId(), element.getId());
            }
            return getById(null, element.getId());
        }
        return null;
    }

    public void delete(UUID modelId, UUID elementId) {
        jpa.transact(em -> {
            Query query;
            String queryString;
            if (Dialects.isDialectCassandra(em.getEntityManagerFactory(), jpa.getPersistenceUnitName())) {
                 /*
                TODO
                The following query *MUST* be sanitized before being placed in production code.
                The preferred way would be to use bind parameters, however as of Kundera 3.13, they are not properly supported by Kundera in our use case.
                See https://github.com/Impetus/Kundera/issues/1029
                */
                //The schema is auto-generated with the id column named key
                queryString = "DELETE FROM elements WHERE key = " + elementId;
                if (modelId != null) {
                    queryString += " AND model_id = " + modelId;
                }
                query = em.createNativeQuery(queryString);
            }
            else {
                queryString = "DELETE FROM Element e WHERE e.id = :elementId";
                if (modelId != null) {
                    queryString += " AND e.model.id = :modelId";
                }
                query = em.createQuery(queryString);
                if (modelId != null) {
                    query.setParameter("modelId", modelId);
                }
                query.setParameter("elementId", elementId);
            }
            query.executeUpdate();
        });
    }

    public void deleteAll(UUID modelId) {
        jpa.transact(em -> {
            Query query;
            String queryString;
            if (modelId != null && Dialects.isDialectCassandra(em.getEntityManagerFactory(), jpa.getPersistenceUnitName())) {
                /*
                TODO
                The following query *MUST* be sanitized before being placed in production code.
                The preferred way would be to use bind parameters, however as of Kundera 3.13, they are not properly supported by Kundera in our use case.
                See https://github.com/Impetus/Kundera/issues/1029
                */
                queryString = "DELETE FROM elements WHERE model_id = " + modelId;
                query = em.createNativeQuery(queryString);
            }
            else {
                queryString = "DELETE FROM Element e";
                if (modelId != null) {
                    queryString += " WHERE e.model.id = :modelId";
                }
                query = em.createQuery(queryString);
                if (modelId != null) {
                    query.setParameter("modelId", modelId);
                }
            }
            query.executeUpdate();
        });
    }

    /**
     * @param modelId   the uuid of the model containing the element, can be null
     * @param elementId the uuid of the element, must not be null
     * @return The element if located, otherwise null.
     */
    public Element getById(UUID modelId, UUID elementId) {
        List<Element> elements = jpa.transact((Function<EntityManager, List<Element>>) em -> {
            Query query;
            String queryString;
            if (Dialects.isDialectCassandra(em.getEntityManagerFactory(), jpa.getPersistenceUnitName())) {
                /*
                TODO
                The following query *MUST* be sanitized before being placed in production code.
                The preferred way would be to use bind parameters, however as of Kundera 3.13, they are not properly supported by Kundera in our use case.
                See https://github.com/Impetus/Kundera/issues/1029
                */
                //The schema is auto-generated with the id column named key
                queryString = "SELECT * FROM elements WHERE key = " + elementId;
                if (modelId != null) {
                    queryString += " AND model_id = " + modelId;
                }
                query = em.createNativeQuery(queryString, Element.class);
            }
            else {
                queryString = "SELECT e FROM Element e WHERE e.id = :elementId";
                if (modelId != null) {
                    queryString += " AND e.model.id = :modelId";
                }
                query = em.createQuery(queryString, Element.class);
                query.setParameter("elementId", elementId);
                if (modelId != null) {
                    query.setParameter("modelId", modelId);
                }
            }
            return query.getResultList();
        });
        return !elements.isEmpty() ? elements.get(0) : null;
    }

    public List<Element> getAll(UUID modelId) {
        return jpa.transact((Function<EntityManager, List<Element>>) em -> {
            Query query;
            String queryString;
            if (Dialects.isDialectCassandra(em.getEntityManagerFactory(), jpa.getPersistenceUnitName())) {
                 /*
                TODO
                The following query *MUST* be sanitized before being placed in production code.
                The preferred way would be to use bind parameters, however as of Kundera 3.13, they are not properly supported by Kundera in our use case.
                See https://github.com/Impetus/Kundera/issues/1029
                */
                queryString = "SELECT * FROM elements";
                if (modelId != null) {
                    queryString += " WHERE model_id = " + modelId;
                }
                query = em.createNativeQuery(queryString, Element.class);
            }
            else {
                queryString = "SELECT e FROM Element e";
                if (modelId != null) {
                    queryString += " WHERE e.model.id = :modelId";
                }
                query = em.createQuery(queryString, Element.class);
                if (modelId != null) {
                    query.setParameter("modelId", modelId);
                }
            }
            return query.getResultList();
        });
    }

    public Element update(Element element) {
        return jpa.transact(em -> {
            em.merge(element);
            em.flush();
            Model elementModel = element.getModel();
            if (elementModel != null) {
                return getById(elementModel.getId(), element.getId());
            }
            return getById(null, element.getId());
        });
    }

    public List<Element> updateAll(UUID modelId, Collection<Element> deserialized) {
        return jpa.transact(em -> {
            deleteAll(modelId);
            for (Element element : deserialized) {
                em.persist(element);
            }
            return getAll(modelId);
        });
    }
}
