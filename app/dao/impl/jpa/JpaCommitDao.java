/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020 InterCAX LLC
 * Copyright (C) 2020 California Institute of Technology ("Caltech")
 * Copyright (C) 2021 Twingineer LLC
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
import dao.ElementDao;
import javabean.JavaBeanHelper;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.*;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.CommitImpl_;
import org.omg.sysml.lifecycle.impl.DataIdentityImpl;
import org.omg.sysml.lifecycle.impl.DataImpl;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.impl.ElementImpl_;

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
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static jackson.RecordSerialization.IDENTITY_FIELD;
import static org.omg.sysml.metamodel.impl.ElementImpl_.QUALIFIED_NAME;

@Singleton
public class JpaCommitDao extends SimpleJpaDao<Commit, CommitImpl> implements CommitDao {

    private final ElementDao elementDao;
    private final JpaBranchDao branchDao;

    @Inject
    public JpaCommitDao(JPAManager jpaManager, ElementDao elementDao, JpaBranchDao branchDao) {
        super(jpaManager, CommitImpl.class, CommitImpl_.id);
        this.elementDao = elementDao;
        this.branchDao = branchDao;
    }

    // TODO Explore alternative to serializing lazy entity attributes that doesn't involve resolving all proxies one level.
    protected static Commit resolve(Commit commit) {
        commit.getChange().stream()
                .filter(Objects::nonNull)
                .map(DataVersion::getPayload)
                .map(data -> JpaDataDao.resolve(data, Data.class))
                .forEach(e -> {
                });
        return commit;
    }

    @Override
    public Optional<Commit> persist(Commit commit, Branch branch) {
        return persist(commit, branch,
                fnData -> {
                    if (fnData.getId() == null) {
                        throw new IllegalArgumentException(String.format("Element must be referenced by %s", IDENTITY_FIELD));
                    }
                },
                (fnData, fnCache) -> Optional.ofNullable(fnCache.get(fnData.getId())),
                (fnData, fnCommit) -> {
                    UUID id = fnData.getId();
                    // TODO change to dataDao
                    return elementDao.findByCommitAndId(fnCommit, id)
                            .orElseThrow(() -> new NoSuchElementException(
                                    String.format("Element with %s %s not found", IDENTITY_FIELD, id)
                            ));
                }
        );
    }

    @Override
    public Optional<Commit> persistNameResolved(Commit commit, Branch branch) {
        return persist(commit, branch,
                fnData -> {
                    if (fnData.getId() == null && (!(fnData instanceof Element) || ((Element) fnData).getQualifiedName() == null)) {
                        throw new IllegalArgumentException(String.format("Element must be referenced by %s or %s", IDENTITY_FIELD, QUALIFIED_NAME));
                    }
                },
                (fnData, fnCache) -> {
                    if (fnData.getId() != null) {
                        return Optional.ofNullable(fnCache.get(fnData.getId()));
                    }
                    else {
                        return fnCache.values().stream()
                                .filter(cached -> cached instanceof Element)
                                .map(cached -> (Element) cached)
                                .filter(cached -> ((Element) fnData).getQualifiedName().equals(cached.getQualifiedName()))
                                .map(cached -> (Data) cached)
                                .findFirst();
                    }
                },
                (fnData, fnCommit) -> {
                    if (fnData.getId() != null) {
                        UUID id = fnData.getId();
                        // TODO change to dataDao
                        return elementDao.findByCommitAndId(fnCommit, id)
                                .orElseThrow(() -> new NoSuchElementException(
                                        String.format("Element with %s %s not found", IDENTITY_FIELD, id)
                                ));
                    }
                    else {
                        String qualifiedName = ((Element) fnData).getQualifiedName();
                        return elementDao.findByCommitAndQualifiedName(fnCommit, qualifiedName)
                                .orElseThrow(() -> new NoSuchElementException(
                                        String.format("Element with %s %s not found", QUALIFIED_NAME, qualifiedName)
                                ));
                    }
                });
    }

    private Optional<Commit> persist(
            Commit commit, Branch branch,
            Consumer<Data> validator,
            BiFunction<Data, Map<UUID, Data>, Optional<Data>> cacheResolver,
            BiFunction<Data, Commit, Data> commitResolver) {
        commit.setPreviousCommit(null);
        if (branch.getHead() != null) {
            commit.setPreviousCommit(branch.getHead());
        }

        Data tombstone = new Data() {
            final UUID id = UUID.randomUUID();

            @Override
            public UUID getId() {
                return id;
            }

            @Override
            public void setId(UUID id) {
                throw new UnsupportedOperationException();
            }
        };

        // Give all Commit#changes an identity, if they don't already have one, and all Commit#changes#identity an id, if they don't already have one.
        commit.getChange().stream()
                .peek(change -> change.setIdentity(change.getIdentity() != null ? change.getIdentity() : new DataIdentityImpl()))
                .map(DataVersion::getIdentity)
                .filter(identity -> identity instanceof DataIdentityImpl)
                .map(identity -> (DataIdentityImpl) identity)
                .forEach(identity -> identity.setId(identity.getId() != null ? identity.getId() : UUID.randomUUID()));

        // Copy all Commit#change#identity#id to Commit#change#payload#id and assign Commit#change#payload#key to a random UUID
        Map<UUID, Data> identifierToDataMap = commit.getChange().stream()
                .peek(change -> {
                    Data payload = change.getPayload();
                    if (payload == null) {
                        return;
                    }
                    payload.setId(change.getIdentity().getId());
                    if (!(payload instanceof DataImpl)) {
                        throw new IllegalStateException();
                    }
                    ((DataImpl) payload).setKey(UUID.randomUUID());
                })
                .collect(Collectors.toMap(
                        change -> change.getIdentity().getId(),
                        change -> change.getPayload() != null ? change.getPayload() : tombstone)
                );

        Function<Data, Data> reattachDataFunction = data -> {
            validator.accept(data);
            return cacheResolver.apply(data, identifierToDataMap)
                    .map(fnData -> fnData != tombstone ? fnData : null)
                    .orElseGet(() -> {
                        Data resolved = commitResolver.apply(data, commit.getPreviousCommit());
                        identifierToDataMap.put(resolved.getId(), resolved);
                        return resolved;
                    });
        };
        commit.getChange().stream()
                .map(DataVersion::getPayload)
                .filter(Objects::nonNull)
                .forEach(data -> JavaBeanHelper.getBeanProperties(data).values().stream()
                        .filter(property -> property.getReadMethod() != null && property.getWriteMethod() != null)
                        .forEach(property -> {
                            Method getter = property.getReadMethod();
                            Method setter = property.getWriteMethod();
                            Class<?> type = property.getPropertyType();

                            Object originalValue;
                            try {
                                originalValue = getter.invoke(data);
                                final Object newValue;
                                if (Data.class.isAssignableFrom(type)) {
                                    if (!(originalValue instanceof Data)) {
                                        return;
                                    }
                                    newValue = reattachDataFunction.apply((Data) originalValue);
                                }
                                else if (Collection.class.isAssignableFrom(type)) {
                                    Collection<?> originalValueCollection = (Collection<?>) originalValue;
                                    if (originalValueCollection.isEmpty() || originalValueCollection.stream().anyMatch((o -> !(o instanceof Data)))) {
                                        return;
                                    }
                                    final Collection<Object> newValueCollection;
                                    if (List.class.isAssignableFrom(type)) {
                                        newValueCollection = new ArrayList<>();
                                    }
                                    else if (Set.class.isAssignableFrom(type)) {
                                        newValueCollection = new HashSet<>();
                                    }
                                    else if (Collection.class.isAssignableFrom(type)) {
                                        newValueCollection = new ArrayList<>();
                                    }
                                    else {
                                        throw new IllegalStateException("Unknown collection type.");
                                    }
                                    ((Collection<?>) originalValue).stream()
                                            .map(o -> (Data) o)
                                            .map(reattachDataFunction)
                                            .forEachOrdered(newValueCollection::add);
                                    newValue = newValueCollection;
                                }
                                else {
                                    return;
                                }
                                setter.invoke(data, newValue);
                            } catch (ReflectiveOperationException e) {
                                throw new RuntimeException(e);
                            }
                        })
                );

        return jpaManager.transact(em -> {
            commit.getChange().stream()
                    .map(DataVersion::getPayload)
                    .filter(Objects::nonNull)
                    .map(payload -> {
                        try {
                            Data dataFirstPass = payload.getClass().getConstructor().newInstance();
                            if (!(dataFirstPass instanceof DataImpl)) {
                                throw new IllegalStateException();
                            }
                            if (!(payload instanceof DataImpl)) {
                                throw new IllegalStateException();
                            }
                            ((DataImpl) dataFirstPass).setKey(((DataImpl) payload).getKey());
                            return dataFirstPass;
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
            return persistedCommit.map(JpaCommitDao::resolve);
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
            return findByProjectAndId(project, id, em).map(JpaCommitDao::resolve);
        });
    }
}
