/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020  InterCAX LLC
 * Copyright (C) 2020  California Institute of Technology ("Caltech")
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 */

package dao.impl.jpa;

import dao.CommitDao;
import javabean.JavaBeanHelper;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Branch;
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
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class JpaCommitDao extends SimpleJpaDao<Commit, CommitImpl> implements CommitDao {

    // TODO Explore alternative to serializing lazy entity attributes that doesn't involve resolving all proxies one level.
    static UnaryOperator<Commit> PROXY_RESOLVER = commit -> {
        commit.getChange().stream()
                .filter(Objects::nonNull)
                .map(ElementVersion::getData)
                .filter(mof -> mof instanceof Element)
                .map(mof -> (Element) mof)
                .map(JpaElementDao.PROXY_RESOLVER)
                .forEach(e -> {
                });
        return commit;
    };

    private final JpaElementDao elementDao;
    private final JpaBranchDao branchDao;

    @Inject
    public JpaCommitDao(JPAManager jpaManager, JpaElementDao elementDao, JpaBranchDao branchDao) {
        super(jpaManager, CommitImpl.class, CommitImpl_.id);
        this.elementDao = elementDao;
        this.branchDao = branchDao;
    }

    @Override
    public Optional<Commit> persist(Commit commit, Branch branch) {
        commit.setPreviousCommit(null);
        if (branch.getHead() != null) {
            commit.setPreviousCommit(branch.getHead());
        }

        MofObject tombstone = new MofObjectImpl() {
            final UUID identifier = UUID.randomUUID();

            @Override
            public UUID getIdentifier() {
                return identifier;
            }

            @Override
            public void setIdentifier(UUID identifier) {

            }
        };

        Supplier<Stream<ElementVersionImpl>> changeStream = () -> commit.getChange().stream().filter(change -> change instanceof ElementVersionImpl).map(change -> (ElementVersionImpl) change);

        // Give all Commit#changes an identity, if they don't already have one, and all Commit#changes#identity an id, if they don't already have one.
        changeStream.get()
                .peek(change -> change.setIdentity(change.getIdentity() != null ? change.getIdentity() : new ElementIdentityImpl())).map(ElementVersion::getIdentity)
                .filter(identity -> identity instanceof ElementIdentityImpl)
                .map(identity -> (ElementIdentityImpl) identity)
                .forEach(identity -> identity.setId(identity.getId() != null ? identity.getId() : UUID.randomUUID()));

        // Copy all Commit#changes#identity#id to Commit#changes#data#identifier and give all Commit#changes#data a random id.
        Map<UUID, MofObject> identifierToMofMap = changeStream.get()
                .peek(change -> Optional.ofNullable(change.getData())
                        .filter(mof -> mof instanceof MofObjectImpl).map(mof -> (MofObjectImpl) mof).ifPresent(mof -> {
                            mof.setIdentifier(change.getIdentity().getId());
                            mof.setKey(UUID.randomUUID());
                        }))
                .collect(Collectors.toMap(change -> change.getIdentity().getId(), change -> Optional.ofNullable(change.getData()).orElse(tombstone)));

        Function<MofObject, MofObject> reattachMofFunction = mof -> {
            MofObject reattachedMof = identifierToMofMap.computeIfAbsent(mof.getIdentifier(), identifier -> {
                if (commit.getPreviousCommit() == null) {
                    return tombstone;
                }
                return elementDao.findByCommitAndId(commit.getPreviousCommit(), identifier)
                        .map(element -> (MofObject) element)
                        .orElse(tombstone);
            });
            if (Objects.equals(reattachedMof, tombstone)) {
                throw new IllegalArgumentException("Element with ID " + mof.getIdentifier() + " not found");
            }
            return reattachedMof;
        };
        changeStream.get()
                .map(ElementVersion::getData)
                .filter(Objects::nonNull)
                .forEach(mof -> JavaBeanHelper.getBeanProperties(mof).values().stream()
                        .filter(property -> property.getReadMethod() != null && property.getWriteMethod() != null)
                        .forEach(property -> {
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
                        })
                );

        return jpaManager.transact(em -> {
            commit.getChange().stream()
                    .map(ElementVersion::getData)
                    .filter(mof -> mof instanceof MofObjectImpl)
                    .map(mof -> (MofObjectImpl) mof)
                    .map(mof -> {
                        try {
                            MofObjectImpl firstPassMof = mof.getClass().getConstructor().newInstance();
                            firstPassMof.setKey(mof.getKey());
                            return firstPassMof;
                        } catch (ReflectiveOperationException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .forEach(em::merge);
            commit.setChange(
                    commit.getChange().stream()
                            .map(em::merge)
                            .collect(Collectors.toSet())
            );
            Optional<Commit> persistedCommit = super.persist(commit, em);
            persistedCommit.ifPresent(c -> {
                branch.setHead(c);
                branchDao.update(branch, em);
            });
            return persistedCommit.map(PROXY_RESOLVER);
        });
    }

    @Override
    public List<Commit> findAllByProject(Project project, UUID after, UUID before, int maxResults) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<CommitImpl> query = builder.createQuery(CommitImpl.class);
            Root<CommitImpl> root = query.from(CommitImpl.class);
            query.select(root);
            Expression<Boolean> where = builder.equal(root.get(CommitImpl_.owningProject), project);
            Paginated<TypedQuery<CommitImpl>> paginated = paginateQuery(after, before, maxResults, query, builder, em, root.get(CommitImpl_.id), where);
            List<Commit> result = paginated.get()
                    .getResultStream()
                    .collect(Collectors.toList());
            if (paginated.isReversed()) {
                Collections.reverse(result);
            }
            return result;
        });
    }

    @Override
    public Optional<Commit> findByProjectAndId(Project project, UUID id) {
        return jpaManager.transact(em -> {
            return findByProjectAndId(project, id, em);
        });
    }

    protected Optional<Commit> findByProjectAndId(Project project, UUID id, EntityManager em) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CommitImpl> query = builder.createQuery(CommitImpl.class);
        Root<CommitImpl> root = query.from(CommitImpl.class);
        query.select(root)
                .where(builder.and(
                        builder.equal(root.get(CommitImpl_.owningProject), project),
                        builder.equal(root.get(CommitImpl_.id), id)
                ));
        Optional<Commit> commit;
        try {
            commit = Optional.of(em.createQuery(query).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
        return commit;
    }

    @Override
    public Optional<Commit> findByProjectAndIdResolved(Project project, UUID id) {
        return jpaManager.transact(em -> {
            return findByProjectAndId(project, id, em).map(PROXY_RESOLVER);
        });
    }
}
