package dao.impl.jpa;

import config.MetamodelProvider;
import dao.ElementDao;
import dao.RelationshipDao;
import jpa.manager.JPAManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.impl.MofObjectImpl;
import org.omg.sysml.metamodel.impl.MofObjectImpl_;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.util.*;
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
                    builder.equal(root.get(MofObjectImpl_.id), id),
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

    // TODO Change to use relatedElement when derived attributes are implemented
    // TODO Implement. The @ManyToAny (polymorphism plus OneToMany in conjunction) of source and target makes this surprisingly difficult. Deferring to after versioning concept is introduced as that will likely break anything we implement now.
    @Override
    public List<Relationship> findAllByRelatedElement(Element element) {
        /*try (Session session = jpa.getEntityManagerFactory().unwrap(SessionFactory.class).openSession()) {
            Query<Relationship> query = session.createQuery("SELECT relationship FROM org.omg.sysml.metamodel.Relationship AS relationship JOIN relationship.source AS s JOIN org.omg.sysml.metamodel.Element AS ss ON s.identifier = ss.identifier WHERE ss.identifier = :elementIdentifier", Relationship.class);
            Query<Relationship> query = session.createQuery("FROM org.omg.sysml.metamodel.Relationship", Relationship.class);
            query.setParameter("elementIdentifier", element.getIdentifier());
            return query.getResultList();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Relationship> query = builder.createQuery(Relationship.class);
            Root<Relationship> root = query.from(Relationship.class);
            query.select(root).where(
                    builder.or(
                            builder.isMember(element, root.get("source")),
                            builder.isMember(element, root.get("target"))
                    )
            );
            return session.createQuery(query).getResultList();


            // Iterate types and SQL (union *_source and *_target, join mofobject and typetable)
            /eturn getTypeStream().map(type -> {
                System.out.println("FOO " + type.getName());
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Relationship> query = (CriteriaQuery<Relationship>) builder.createQuery(type);
                Root<Relationship> root = (Root<Relationship>) query.from(type);
                query.select(root).where(
                        builder.or(
                                builder.isMember(element.getIdentifier(), root.get("source").get(MofObjectImpl_.IDENTIFIER)),
                                builder.isMember(element.getIdentifier(), root.get("target").get(MofObjectImpl_.IDENTIFIER))
                        )
                );
                return session.createQuery(query).getResultList();
            }).flatMap(Collection::stream).collect(Collectors.toList());
        }

        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Relationship> query = builder.createQuery(Relationship.class);
            Root<RelationshipImpl> root = query.from(RelationshipImpl.class);
            query.select(root).where(
                    builder.or(
                            builder.isMember(element, root.get(RelationshipImpl_.source)),
                            builder.isMember(element, root.get(RelationshipImpl_.target))
                    )
            );
            return em.createQuery(query).getResultList();
        });

        return jpa.transact(em -> {
            Query query = em.createNativeQuery("SELECT CAST(Ownership.identifier AS text) FROM Ownership_source UNION INNER JOIN Ownership ON (Ownership.identifier = Ownership_source.OwnershipId) WHERE Ownership_source.sourceId = :elementIdentifier");
            query.setParameter("elementIdentifier", element.getIdentifier());
            List<String> relationships = query.getResultList();
            System.out.println(relationships);
            return Collections.emptyList();
            //return query.getResultList();
        });
         */
        return Collections.emptyList();
    }

    @Override
    public List<Relationship> findAllBySourceElement(Element element) {
        /*
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Relationship> query = builder.createQuery(Relationship.class);
            Root<RelationshipImpl> root = query.from(RelationshipImpl.class);
            query.select(root).where(builder.isMember(element, root.get(RelationshipImpl_.source)));
            return em.createQuery(query).getResultList();
        });
         */
        return Collections.emptyList();
    }

    @Override
    public List<Relationship> findAllByTargetElement(Element element) {
        /*
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Relationship> query = builder.createQuery(Relationship.class);
            Root<RelationshipImpl> root = query.from(RelationshipImpl.class);
            query.select(root).where(builder.isMember(element, root.get(RelationshipImpl_.target)));
            return em.createQuery(query).getResultList();
        });
         */
        return Collections.emptyList();
    }

    @Override
    public List<Relationship> findAllByProject(Project project) {
        try (Session session = jpa.getEntityManagerFactory().unwrap(SessionFactory.class).openSession()) {
            org.hibernate.query.Query<Relationship> query = session.createQuery("FROM org.omg.sysml.metamodel.Relationship WHERE containingProject = :project", Relationship.class);
            query.setParameter("project", project);
            return query.getResultList();
        }
    }

    @Override
    public Set<Relationship> findAllByCommitRelatedElement(Commit commit, Element relatedElement) {
        return jpa.transact(em -> {
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(metamodelProvider.getImplementationClass(Commit.class), commit.getId());
            return elementDao.streamFlattenedElements(c).peek(System.out::println).filter(e -> e instanceof Relationship).map(e -> (Relationship) e)
                    .filter(relationship -> Stream.concat(relationship.getSource().stream(), relationship.getTarget().stream()).map(Element::getIdentifier).anyMatch(id -> id.equals(relatedElement.getIdentifier())))
                    .collect(Collectors.toSet());
        });
    }

    private Stream<Class<?>> getTypeStream() {
        return metamodelProvider.getAllImplementationClasses().stream().filter(Relationship.class::isAssignableFrom);
    }

    private Expression<Boolean> getTypeExpression(CriteriaBuilder builder, Root<?> root) {
        return builder.or(getTypeStream().map(c -> builder.equal(root.type(), c)).toArray(Predicate[]::new));
    }
}
