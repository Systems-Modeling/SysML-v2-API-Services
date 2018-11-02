package dao;

import jpa.Dialects;
import jpa.KunderaManager;
import models.Model;
import models.Relationship;
import models.RelationshipEndType;
import models.SourceTargetQuery;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
            //The JPA spec says that you can have annotations placed on getters/setters to cause them to be used instead of direct field access.
            //Kundera violates this and forces the annotations to be placed on the field. To force proper initialization, we thus manually invoke the getter.
            relationship.getSourceTargetQuery();
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

    public Relationship getBySourceAndTarget(UUID modelId, UUID sourceId, UUID targetId) {
        return jpa.transact(em -> {
            String queryString;
            Query query;
            if (Dialects.isDialectCassandra(em.getEntityManagerFactory(), jpa.getPersistenceUnitName())) {
                /*
                TODO
                The following query *MUST* be sanitized before being placed in production code.
                The preferred way would be to use bind parameters, however as of Kundera 3.13, they are not properly supported by Kundera in our use case.
                See https://github.com/Impetus/Kundera/issues/1029
                */
                queryString = "SELECT * FROM source_target_query WHERE source_id = " + sourceId + " AND target_id = " + targetId;
                query = em.createNativeQuery(queryString, SourceTargetQuery.class);
            }
            else {
                queryString = "SELECT q FROM SourceTargetQuery q WHERE q.source.id = :source_id AND q.target.id = :target_id";
                query = em.createQuery(queryString, SourceTargetQuery.class);
                query.setParameter("source_id", sourceId);
                query.setParameter("target_id", targetId);
            }
            SourceTargetQuery sourceTargetQuery;
            try {
                sourceTargetQuery = (SourceTargetQuery) query.getSingleResult();
            } catch (NoResultException nre) {
                return null;
            }
            if (sourceTargetQuery == null) {
                return null;
            }
            UUID relationshipId = sourceTargetQuery.getRelationshipId();
            if (Dialects.isDialectCassandra(em.getEntityManagerFactory(), jpa.getPersistenceUnitName())) {
                queryString = "SELECT * FROM relationships WHERE key = " + relationshipId;
                if (modelId != null) {
                    queryString += " AND model_id = " + modelId;
                }
                query = em.createNativeQuery(queryString, Relationship.class);
            }
            else {
                queryString = "SELECT r FROM Relationship r WHERE r.id = :relationship_id";
                if (modelId != null) {
                    queryString += " AND r.model.id = :model_id";
                }
                query = em.createQuery(queryString, Relationship.class);
                query.setParameter("relationship_id", relationshipId);
                if (modelId != null) {
                    query.setParameter("model_id", modelId);
                }
            }
            try {
                return (Relationship) query.getSingleResult();
            } catch (NoResultException nre) {
                return null;
            }
        });
    }

    public Relationship update(Relationship relationship) {
        return jpa.transact((entityManager) -> {
            relationship.getSourceTargetQuery();
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
                relationship.getSourceTargetQuery();
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
