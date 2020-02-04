package dao.impl.jpa;

import dao.CommitDao;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.ElementVersion;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.CommitImpl_;
import org.omg.sysml.lifecycle.impl.ElementIdentityImpl;
import org.omg.sysml.lifecycle.impl.ElementVersionImpl;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.metamodel.impl.MofObjectImpl;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class JpaCommitDao extends JpaDao<Commit> implements CommitDao {
    @Inject
    private JPAManager jpa;

    @Override
    protected JPAManager getJpaManager() {
        return jpa;
    }

    @Override
    public Optional<Commit> persist(Commit commit) {
        UUID tombstoneUuid = UUID.randomUUID();

        Supplier<Stream<ElementVersionImpl>> changeStream = () -> commit.getChanges().stream().filter(change -> change instanceof ElementVersionImpl).map(change -> (ElementVersionImpl) change);

        // Give all Commit#changes an identity, if they don't already have one, and all Commit#changes#identity an id, if they don't already have one.
        changeStream.get().peek(change -> change.setIdentity(change.getIdentity() != null ? change.getIdentity() : new ElementIdentityImpl())).map(ElementVersion::getIdentity).filter(identity -> identity instanceof ElementIdentityImpl).map(identity -> (ElementIdentityImpl) identity).forEach(identity -> identity.setId(identity.getId() != null ? identity.getId() : UUID.randomUUID()));

        // Copy all Commit#changes#identity#id to Commit#changes#data#identifier and give all Commit#changes#data a random id.
        Map<UUID, UUID> identifierToIdMap = changeStream.get().peek(change -> Optional.ofNullable(change.getData()).filter(mof -> mof instanceof MofObjectImpl).map(mof -> (MofObjectImpl) mof).ifPresent(mof -> {
            mof.setIdentifier(change.getIdentity().getId());
            mof.setId(UUID.randomUUID());
        })).collect(Collectors.toMap(change -> change.getIdentity().getId(), change -> Optional.ofNullable(change.getData()).map(MofObject::getId).orElse(tombstoneUuid)));

        // TODO Resolve/set all identifier -> id, reflectively. Hopefully that's enough for Hibernate to try to make the relationships and give a meaningful error when types are incompatible.

        if (!(commit instanceof CommitImpl)) {
            throw new IllegalStateException();
        }
        return jpa.transact(em -> {
            commit.getChanges().stream().map(ElementVersion::getData).filter(mof -> mof instanceof MofObjectImpl).map(mof -> (MofObjectImpl) mof).map(mof -> {
                try {
                    MofObjectImpl firstPassMof = mof.getClass().getConstructor().newInstance();
                    firstPassMof.setId(mof.getId());
                    return firstPassMof;
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException(e);
                }
            }).forEach(em::merge);
            commit.setChanges(commit.getChanges().stream().map(em::merge).collect(Collectors.toSet()));
            return super.persist(commit, em);
        });
    }

    @Override
    public Optional<Commit> findById(UUID id) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<CommitImpl> query = builder.createQuery(CommitImpl.class);
            Root<CommitImpl> root = query.from(CommitImpl.class);
            query.select(root)
                    .where(builder.equal(root.get(CommitImpl_.id), id));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Commit> findAll() {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<CommitImpl> query = builder.createQuery(CommitImpl.class);
            query.select(query.from(CommitImpl.class));
            return em.createQuery(query).getResultStream().collect(Collectors.toList());
        });
    }

    @Override
    public void deleteAll() {
        jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaDelete<CommitImpl> query = builder.createCriteriaDelete(CommitImpl.class);
            query.from(CommitImpl.class);
            return ((Stream<?>) em.createQuery(query).getResultStream()).collect(Collectors.toList());
        });
    }

    @Override
    public List<Commit> findAllByProject(Project project) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<CommitImpl> query = builder.createQuery(CommitImpl.class);
            Root<CommitImpl> root = query.from(CommitImpl.class);
            query.select(root)
                    .where(builder.equal(root.get(CommitImpl_.containingProject), project));
            return em.createQuery(query).getResultStream().collect(Collectors.toList());
        });
    }

    @Override
    public Optional<Commit> findByProjectAndId(Project project, UUID id) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<CommitImpl> query = builder.createQuery(CommitImpl.class);
            Root<CommitImpl> root = query.from(CommitImpl.class);
            query.select(root)
                    .where(builder.and(
                            builder.equal(root.get(CommitImpl_.containingProject), project),
                            builder.equal(root.get(CommitImpl_.id), id)
                    ));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }
}
