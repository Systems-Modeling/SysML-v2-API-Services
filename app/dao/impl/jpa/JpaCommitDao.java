package dao.impl.jpa;

import dao.CommitDao;
import javabean.JavaBeanHelper;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.ElementVersion;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.CommitImpl_;
import org.omg.sysml.lifecycle.impl.ElementIdentityImpl;
import org.omg.sysml.lifecycle.impl.ElementVersionImpl;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.metamodel.impl.MofObjectImpl;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class JpaCommitDao extends JpaDao<Commit> implements CommitDao {
    // TODO Explore alternative to serializing lazy entity attributes that doesn't involve resolving all proxies one level.
    static Consumer<Commit> PROXY_RESOLVER = commit -> commit.getChanges().stream().filter(Objects::nonNull).map(ElementVersion::getData).filter(mof -> mof instanceof Element).map(mof -> (Element) mof).forEach(JpaElementDao.PROXY_RESOLVER);

    @Inject
    private JPAManager jpa;

    @Override
    protected JPAManager getJpaManager() {
        return jpa;
    }

    private JpaElementDao elementDao = new JpaElementDao();

    @Override
    public Optional<Commit> persist(Commit commit) {
        MofObject tombstone = new MofObjectImpl() {
            UUID id = UUID.randomUUID();

            @Override
            public UUID getId() {
                return id;
            }
        };

        Supplier<Stream<ElementVersionImpl>> changeStream = () -> commit.getChanges().stream().filter(change -> change instanceof ElementVersionImpl).map(change -> (ElementVersionImpl) change);

        // Give all Commit#changes an identity, if they don't already have one, and all Commit#changes#identity an id, if they don't already have one.
        changeStream.get().peek(change -> change.setIdentity(change.getIdentity() != null ? change.getIdentity() : new ElementIdentityImpl())).map(ElementVersion::getIdentity).filter(identity -> identity instanceof ElementIdentityImpl).map(identity -> (ElementIdentityImpl) identity).forEach(identity -> identity.setId(identity.getId() != null ? identity.getId() : UUID.randomUUID()));

        // Copy all Commit#changes#identity#id to Commit#changes#data#identifier and give all Commit#changes#data a random id.
        Map<UUID, MofObject> identifierToMofMap = changeStream.get().peek(change -> Optional.ofNullable(change.getData()).filter(mof -> mof instanceof MofObjectImpl).map(mof -> (MofObjectImpl) mof).ifPresent(mof -> {
            mof.setIdentifier(change.getIdentity().getId());
            mof.setId(UUID.randomUUID());
        })).collect(Collectors.toMap(change -> change.getIdentity().getId(), change -> Optional.ofNullable(change.getData()).orElse(tombstone)));

        // Attempt #1 using a javassist proxy. Failed because Hibernate/JPA can't handle subclasses of Entities.
/*        changeStream.get().map(ElementVersion::getData).filter(Objects::nonNull).map(JpaCommitDao::getBeanPropertyValues).flatMap(map -> map.values().stream()).flatMap(o -> o instanceof Collection ? ((Collection<?>) o).stream() : Stream.of(o)).filter(o -> o instanceof MofObject && o instanceof ProxyObject).map(o -> (MofObject & ProxyObject) o).forEach(mofProxy -> {
            MofObject mof = identifierToMofMap.computeIfAbsent(mofProxy.getIdentifier(), identifier -> {
                if (commit.getPreviousCommit() == null) {
                    return tombstone;
                }
                return elementDao.findByCommitAndId(commit.getPreviousCommit(), identifier).map(element -> (MofObject) element).orElse(tombstone);
            });
            if (Objects.equals(mof, tombstone)) {
                throw new IllegalArgumentException("Element with ID " + mofProxy.getIdentifier() + " not found.");
            }
            mofProxy.setHandler(new PassthroughMethodHandler(mof));
            System.out.println("REFERENCE: " + mofProxy.getIdentifier());
        });*/

        Function<MofObject, MofObject> reattachMofFunction = mof -> {
            MofObject reattachedMof = identifierToMofMap.computeIfAbsent(mof.getIdentifier(), identifier -> {
                if (commit.getPreviousCommit() == null) {
                    return tombstone;
                }
                return elementDao.findByCommitAndId(commit.getPreviousCommit(), identifier).map(element -> (MofObject) element).orElse(tombstone);
            });
            if (Objects.equals(reattachedMof, tombstone)) {
                throw new IllegalArgumentException("Element with ID " + mof.getIdentifier() + " not found.");
            }
            return reattachedMof;
        };
        changeStream.get().map(ElementVersion::getData).filter(Objects::nonNull).forEach(mof -> JavaBeanHelper.getBeanProperties(mof).values().stream().filter(property -> property.getReadMethod() != null && property.getWriteMethod() != null).forEach(property -> {
            Method getter = property.getReadMethod();
            Method setter = property.getWriteMethod();
            Class<?> type = property.getPropertyType();

            Object originalValue;
            try {
                originalValue = getter.invoke(mof);
                final Object newValue;
                if (MofObject.class.isAssignableFrom(type)) {
                    if (!(originalValue instanceof MofObject)) {
                        return;
                    }
                    newValue = reattachMofFunction.apply((MofObject) originalValue);
                } else if (Collection.class.isAssignableFrom(type)) {
                    Collection<?> originalValueCollection = (Collection<?>) originalValue;
                    if (originalValueCollection.isEmpty() || originalValueCollection.stream().anyMatch((o -> !(o instanceof MofObject)))) {
                        return;
                    }
                    final Collection<Object> newValueCollection;
                    if (List.class.isAssignableFrom(type)) {
                        newValueCollection = new ArrayList<>();
                    } else if (Set.class.isAssignableFrom(type)) {
                        newValueCollection = new HashSet<>();
                    } else if (Collection.class.isAssignableFrom(type)) {
                        newValueCollection = new ArrayList<>();
                    } else {
                        throw new IllegalStateException("Unknown collection type.");
                    }
                    ((Collection<?>) originalValue).stream().map(o -> (MofObject) o).map(reattachMofFunction).forEachOrdered(newValueCollection::add);
                    newValue = newValueCollection;
                } else {
                    return;
                }
                setter.invoke(mof, newValue);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }));

 /*       changeStream.get().map(ElementVersion::getData).filter(Objects::nonNull).filter(element -> element instanceof BlockImpl).map(element -> (BlockImpl) element).forEach(block -> {
            if (block.getOwner() instanceof MofObjectImpl) {
                MofObjectImpl owner = (MofObjectImpl) block.getOwner();
                if (owner.getIdentifier() != null) {
                    block.setOwner((Element) identifierToMofMap.get(owner.getIdentifier()));
                }
            }
        });*/

        // If previousCommit is not specified, default to head commit.
        if (commit.getPreviousCommit() == null && commit.getContainingProject() != null) {
            findHeadByProject(commit.getContainingProject()).ifPresent(commit::setPreviousCommit);
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
            Optional<Commit> commit;
            try {
                commit = Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
            commit.ifPresent(PROXY_RESOLVER);
            return commit;
        });
    }

    @Override
    public Optional<Commit> findHeadByProject(Project project) {
        return jpa.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<CommitImpl> query = builder.createQuery(CommitImpl.class);
            Root<CommitImpl> root = query.from(CommitImpl.class);
            query.select(root)
                    .where(builder.equal(root.get(CommitImpl_.containingProject), project))
                    .orderBy(builder.desc(root.get(CommitImpl_.timestamp)));
            Optional<Commit> commit;
            try {
                commit = Optional.of(em.createQuery(query).setMaxResults(1).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
            commit.ifPresent(PROXY_RESOLVER);
            return commit;
        });
    }

/*    private static class PassthroughMethodHandler implements MethodHandler {

        private final Object target;

        protected PassthroughMethodHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
            //return thisMethod.invoke(target, args);
            return target.getClass().getMethod(thisMethod.getName(), thisMethod.getParameterTypes()).invoke(target, args);
        }
    }*/
}
