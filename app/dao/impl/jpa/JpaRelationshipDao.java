package dao.impl.jpa;

import config.MetamodelProvider;
import dao.RelationshipDao;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.ElementVersion;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.impl.MofObjectImpl;
import org.omg.sysml.metamodel.impl.MofObjectImpl_;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class JpaRelationshipDao extends JpaDao<Relationship> implements RelationshipDao {
    @Inject
    private MetamodelProvider metamodelProvider;

    @Inject
    private JPAManager jpa;

    private JpaElementDao elementDao = new JpaElementDao();

    @Override
    protected JPAManager getJpaManager() {
        return jpa;
    }

    @Override
    public Optional<Relationship> findById(UUID id) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<MofObjectImpl> query = builder.createQuery(MofObjectImpl.class);
            Root<MofObjectImpl> root = query.from(MofObjectImpl.class);
            query.select(root).where(builder.and(
                    builder.equal(root.get(MofObjectImpl_.identifier), id),
                    getTypeExpression(builder, root)
            ));
            try {
                return Optional.of((Relationship) em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Relationship> findAll() {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<MofObjectImpl> query = builder.createQuery(MofObjectImpl.class);
            Root<MofObjectImpl> root = query.from(MofObjectImpl.class);
            query.select(root).where(getTypeExpression(builder, root));
            return em.createQuery(query).getResultStream().map(o -> (Relationship) o).collect(Collectors.toList());
        });
    }

    @Override
    public void deleteAll() {
        jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaDelete<MofObjectImpl> query = builder.createCriteriaDelete(MofObjectImpl.class);
            Root<MofObjectImpl> root = query.from(MofObjectImpl.class);
            query.where(getTypeExpression(builder, root));
            return ((Stream<?>) em.createQuery(query).getResultStream()).map(o -> (Relationship) o).collect(Collectors.toList());
        });
    }

    @Override
    public Set<Relationship> findAllByCommitRelatedElement(Commit commit, Element relatedElement) {
        return jpa.transact(em -> {
/*            Commit c = em.contains(commit) ? commit : em.find(metamodelProvider.getImplementationClass(Commit.class), commit.getId());
            return elementDao.streamFlattenedElements(c).filter(e -> e instanceof Relationship).map(e -> (Relationship) e)
                    .filter(relationship -> Stream.concat(relationship.getSource().stream(), relationship.getTarget().stream()).map(Element::getIdentifier).anyMatch(id -> id.equals(relatedElement.getIdentifier())))
                    .collect(Collectors.toSet());
        });*/

        /*return jpa.transact(em -> {
            CommitIndex commitIndex = elementDao.getCommitIndex(commit, em);

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<ElementVersionImpl> query = builder.createQuery(ElementVersionImpl.class);
            Root<CommitIndexImpl> commitIndexRoot = query.from(CommitIndexImpl.class);
            SetJoin<CommitIndexImpl, ElementVersionImpl> workingElementVersionsJoin = commitIndexRoot.join(CommitIndexImpl_.workingElementVersions);
            Join<ElementVersionImpl, MofObjectImpl> dataJoin = workingElementVersionsJoin.join(ElementVersionImpl_.identity);
            query.select(workingElementVersionsJoin).where(
                    builder.equal(commitIndexRoot.get(CommitIndexImpl_.id), commitIndex.getId()),
                    builder.equal(elementIdentityJoin.get(ElementIdentityImpl_.id), id)
            );
            try {
                return Optional.of(em.createQuery(query).getSingleResult()).map(ElementVersion::getData).filter(mof -> mof instanceof Element).map(mof -> (Element) mof);
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });*/
/*            Commit c = em.contains(commit) ? commit : em.find(metamodelProvider.getImplementationClass(Commit.class), commit.getId());
            CommitIndex commitIndex = elementDao.getCommitIndex(c, em);

            // Switching from Criteria API to HQL, because JPA 2 can't handle @Any for ElementVersion#data and no static metamodel is generated.
            // https://hibernate.atlassian.net/browse/HHH-6589
            Session session = em.unwrap(Session.class);

            Query<?> query2 = session.createQuery(
                    "select relationship, source, target " +
                            "from RelationshipImpl as relationship " +
                            "join relationship.source source " +
                            "join relationship.target target " +
                            "where relationship.id in (" +
                                "select elementVersion.data.id " +
                                "from org.omg.sysml.internal.impl.CommitIndexImpl as commitIndex " +
                                "join commitIndex.workingElementVersions as elementVersion " +
                                "where commitIndex.id = :commitIndexId" +
                            ")");
            query2.setParameter("commitIndexId", commitIndex.getId());
            query2.setParameter("relatedElementIdentifier", relatedElement.getIdentifier());
            query2.list().forEach(System.out::println);*/

            // Polymorphism ruins the day again. Attributes of @Any and @ManyToAny attributes can't be used in where or join clauses as their type is not known. Mitigating with nested queries.
            // Note that nested queries can only be used in select (but not from) and where clauses.

/*            Query<Relationship> query = session.createQuery(
                    "select relationship " +
                            "from org.omg.sysml.internal.impl.CommitIndexImpl as commitIndex, RelationshipImpl relationship " +
                            "join commitIndex.workingElementVersions as elementVersion " +
                            "join elementVersion.data as data " +
                            "join relationship.source as source " +
                            "join relationship.target as target " +
                            "where commitIndex.id = :commitIndexId " +
                            "and relationship.id = data.id " +
                            "and relationship.class in :types " +
                            "and (source.identifier = :relatedElementIdentifier or target.identifier = :relatedElementIdentifier)",
                    Relationship.class);
            Query<Relationship> query = session.createQuery(
                    "select relationship " +
                            "from CommitIndexImpl commitIndex, ElementVersionImpl elementVersion, RelationshipImpl relationship " +
                            "where commitIndex.id = :commitIndexId " +
                            "and elementVersion. = ",
                    Relationship.class);
            );
            query.setParameter("commitIndexId", commitIndex.getId());
            query.setParameter("types", getTypeStream().map(Class::getCanonicalName).collect(Collectors.toList()));
            query.setParameter("relatedElementIdentifier", relatedElement.getIdentifier());
            return new LinkedHashSet<>(query.list());*/

/*            Session session = em.unwrap(Session.class);
            Query<UUID> uuidQuery = session.createQuery(
                    "select elementVersion.data.id " +
                            "from org.omg.sysml.internal.impl.CommitIndexImpl as commitIndex " +
                            "join commitIndex.workingElementVersions as elementVersion " +
                            "where commitIndex.id = :commitIndexId",
                    UUID.class);
            uuidQuery.setParameter("commitIndexId", commitIndex.getId());
            List<UUID> uuids = uuidQuery.list();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<RelationshipImpl> query = builder.createQuery(RelationshipImpl.class);
            Root<RelationshipImpl> relationshipRoot = query.from(RelationshipImpl.class);
            // java.lang.IllegalArgumentException: Unable to locate Attribute  with the the given name [source] on this ManagedType [org.omg.sysml.metamodel.impl.MofObjectImpl]
            // Polymorphism strikes again...
            CollectionJoin<RelationshipImpl, Element> sourceJoin = relationshipRoot.joinCollection(RelationshipImpl_.SOURCE, JoinType.LEFT);
            CollectionJoin<RelationshipImpl, Element> targetJoin = relationshipRoot.joinCollection(RelationshipImpl_.TARGET, JoinType.LEFT);

            query.select(relationshipRoot).where(
                    relationshipRoot.get(RelationshipImpl_.id).in(uuids),
                    builder.or(
                            builder.equal(sourceJoin.get(ElementImpl_.IDENTIFIER), relatedElement.getIdentifier()),
                            builder.equal(targetJoin.get(ElementImpl_.IDENTIFIER), relatedElement.getIdentifier())
                    )
            );
            return new LinkedHashSet<>(em.createQuery(query).getResultList());*/

            // Reverting to non-relational streaming
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(metamodelProvider.getImplementationClass(Commit.class), commit.getId());
            return elementDao.getCommitIndex(c, em).getWorkingElementVersions().stream().map(ElementVersion::getData).filter(mof -> mof instanceof Relationship).map(mof -> (Relationship) mof).filter(relationship -> Stream.concat(relationship.getSource().stream(), relationship.getTarget().stream()).map(Element::getIdentifier).anyMatch(id -> id.equals(relatedElement.getIdentifier()))).collect(Collectors.toSet());
        });
    }

    private Stream<Class<?>> getTypeStream() {
        return metamodelProvider.getAllImplementationClasses().stream().filter(Relationship.class::isAssignableFrom);
    }

    private Expression<Boolean> getTypeExpression(CriteriaBuilder builder, Root<?> root) {
        return builder.or(getTypeStream().map(c -> builder.equal(root.type(), c)).toArray(Predicate[]::new));
    }
}
