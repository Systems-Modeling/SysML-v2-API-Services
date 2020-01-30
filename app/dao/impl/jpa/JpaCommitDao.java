package dao.impl.jpa;

import dao.CommitDao;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.metamodel.impl.MofObjectImpl;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.CommitImpl_;
import org.omg.sysml.lifecycle.impl.ElementIdentityImpl;
import org.omg.sysml.lifecycle.impl.ElementRecordImpl;

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
        commit.getChanges().stream().filter(record -> record.getIdentity() == null).filter(record -> record instanceof ElementRecordImpl).map(record -> (ElementRecordImpl) record)
                .forEach(record -> {
                    ElementIdentityImpl identity = new ElementIdentityImpl();
                    identity.setId(UUID.randomUUID());
                    record.setIdentity(identity);
                });
        commit.getChanges().stream().filter(record -> record.getData() != null).filter(record -> record.getData() instanceof MofObjectImpl)
                .forEach(record -> {
                    ((MofObjectImpl) record.getData()).setId(UUID.randomUUID());
                    ((MofObjectImpl) record.getData()).setIdentifier(record.getIdentity().getId());
                });
        if (!(commit instanceof CommitImpl)) {
            throw new IllegalStateException();
        }
        return jpa.transact(em -> {
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
