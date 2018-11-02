package dao;

import jpa.Dialects;
import jpa.KunderaManager;
import models.Model;
import models.Relationship;
import models.RelationshipEndType;

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
public class RelationshipDAO {
    @Inject
    private KunderaManager jpa;

    public Relationship create(Relationship relationship) {
        if (relationship != null) {
            jpa.transact(entityManager -> {
                entityManager.persist(relationship);
                entityManager.flush();
            });
            Model relationshipModel = relationship.getModel();
            if (relationshipModel != null) {
                return getById(relationshipModel.getId(), relationship.getId());
            }
            return getById(null, relationship.getId());
        }
        return null;
    }

    public void delete(UUID modelId, UUID relationshipId) {
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
                queryString = "DELETE FROM relationships WHERE key = " + relationshipId;
                if (modelId != null) {
                    queryString += " AND model_id = " + modelId;
                }
                query = em.createNativeQuery(queryString);
            }
            else {
                queryString = "DELETE FROM Relationship r WHERE r.id = :relationshipId";
                if (modelId != null) {
                    queryString += " AND e.model.id = :modelId";
                }
                query = em.createQuery(queryString);
                if (modelId != null) {
                    query.setParameter("modelId", modelId);
                }
                query.setParameter("relationshipId", relationshipId);
            }
            query.executeUpdate();
        });
    }

    public void deleteAll(UUID modelId) {
        jpa.transact(em -> {
            deleteAll(em, modelId);
        });
    }

    public List<Relationship> getAll(UUID modelId) {
        return jpa.transact((Function<EntityManager, List<Relationship>>) em -> {
            Query query;
            if (Dialects.isDialectCassandra(em.getEntityManagerFactory(), jpa.getPersistenceUnitName())) {
                /*
                TODO
                The following query *MUST* be sanitized before being placed in production code.
                The preferred way would be to use bind parameters, however as of Kundera 3.13, they are not properly supported by Kundera in our use case.
                See https://github.com/Impetus/Kundera/issues/1029
                */
                String queryString = "SELECT * FROM relationships";
                if (modelId != null) {
                    queryString += " WHERE model_id = " + modelId;
                }
                query = em.createNativeQuery(queryString, Relationship.class);
            }
            else {
                String queryString = "SELECT r FROM Relationship r";
                if (modelId != null) {
                    queryString += " WHERE r.model.id = :modelId";
                }
                query = em.createQuery(queryString, Relationship.class);
                if (modelId != null) {
                    query.setParameter("modelId", modelId);
                }
            }
            return query.getResultList();
        });
    }

    public Relationship getById(UUID modelId, UUID relationshipId) {
        return jpa.transact(em -> {
            Query query;
            if (Dialects.isDialectCassandra(em.getEntityManagerFactory(), jpa.getPersistenceUnitName())) {
                /*
                TODO
                The following query *MUST* be sanitized before being placed in production code.
                The preferred way would be to use bind parameters, however as of Kundera 3.13, they are not properly supported by Kundera in our use case.
                See https://github.com/Impetus/Kundera/issues/1029
                */
                //The schema is auto-generated with the id column named key
                String queryString = "SELECT * FROM relationships WHERE key = " + relationshipId;
                if (modelId != null) {
                    queryString += " AND model_id = " + modelId;
                }
                query = em.createNativeQuery(queryString, Relationship.class);
            }
            else {
                String queryString = "SELECT r FROM Relationship r WHERE r.id = :relationshipId";
                if (modelId != null) {
                    queryString += " AND r.model.id = :modelId";
                }
                query = em.createQuery(queryString, Relationship.class);
                query.setParameter("relationshipId", relationshipId);
                if (modelId != null) {
                    query.setParameter("modelId", modelId);
                }
            }
            List<Relationship> response = query.getResultList();
            if (response.size() > 1) {
                throw new IllegalStateException("The result list contained more than one element with the given id.");
            }
            else if (response.isEmpty()) {
                return null;
            }
            return response.get(0);
        });
    }

    public List<Relationship> getBySourceId(UUID modelId, UUID elementId) {
        return getByEndType(modelId, elementId, RelationshipEndType.SOURCE);
    }

    public List<Relationship> getByTargetId(UUID modelId, UUID elementId) {
        return getByEndType(modelId, elementId, RelationshipEndType.TARGET);
    }

    private List<Relationship> getByEndType(UUID modelId, UUID elementId, RelationshipEndType source) {
        return jpa.transact((Function<EntityManager, List<Relationship>>) em -> {
            String queryString;
            Query query;
            if (Dialects.isDialectCassandra(em.getEntityManagerFactory(), jpa.getPersistenceUnitName())) {
                        /*
                        TODO
                        The following query *MUST* be sanitized before being placed in production code.
                        The preferred way would be to use bind parameters, however as of Kundera 3.13, they are not properly supported by Kundera in our use case.
                        See https://github.com/Impetus/Kundera/issues/1029
                        */
                queryString = "SELECT * FROM relationships WHERE " + (source.equals(RelationshipEndType.SOURCE) ? "source_id" : "target_id") + " = " + elementId;
                if (modelId != null) {
                    queryString += " AND model_id = " + modelId;
                }
                query = em.createNativeQuery(queryString, Relationship.class);
            }
            else {
                queryString = "SELECT r FROM Relationship r WHERE r." + (source.equals(RelationshipEndType.SOURCE) ? "source" : "target") + ".id = :elementId";
                if (modelId != null) {
                    queryString += " AND r.model.id = :modelId";
                }
                query = em.createQuery(queryString, Relationship.class);
                query.setParameter("elementId", elementId);
                if (modelId != null) {
                    query.setParameter("modelId", modelId);
                }
            }
            return query.getResultList();
        });
    }

    public Relationship update(Relationship relationship) {
        return jpa.transact((entityManager) -> {
            entityManager.merge(relationship);
            entityManager.flush();
            Model relationshipModel = relationship.getModel();
            if (relationshipModel != null) {
                return getById(relationshipModel.getId(), relationship.getId());
            }
            return getById(null, relationship.getId());
        });
    }

    public List<Relationship> updateAll(UUID modelId, Collection<Relationship> deserialized) {
        return jpa.transact(em -> {
            deleteAll(em, modelId);
            for (Relationship relationship : deserialized) {
                em.persist(relationship);
            }
            return getAll(modelId);
        });
    }

    private void deleteAll(EntityManager em, UUID modelId) {
        Query query;
        String queryString;
        if (modelId != null && Dialects.isDialectCassandra(em.getEntityManagerFactory(), jpa.getPersistenceUnitName())) {
            /*
              TODO
              The following query *MUST* be sanitized before being placed in production code.
              The preferred way would be to use bind parameters, however as of Kundera 3.13, they are not properly supported by Kundera in our use case.
              See https://github.com/Impetus/Kundera/issues/1029
            */
            queryString = "DELETE FROM relationships WHERE model_id = " + modelId;
            query = em.createNativeQuery(queryString);
        }
        else {
            queryString = "DELETE FROM Relationship r";
            if (modelId != null) {
                queryString += " WHERE r.model.id = :modelId";
            }
            query = em.createQuery(queryString);
            if (modelId != null) {
                query.setParameter("modelId", modelId);
            }
        }
        query.executeUpdate();
    }
}
