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

import config.MetamodelProvider;
import dao.RelationshipDao;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.DataVersion;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.metamodel.impl.RelationshipImpl;
import org.omg.sysml.metamodel.impl.RelationshipImpl_;
import org.omg.sysml.util.RelationshipDirection;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public class JpaRelationshipDao extends JpaDao<Relationship> implements RelationshipDao {

    private final JpaDataDao dataDao;
    private final Set<Class<?>> implementationClasses;

    @Inject
    public JpaRelationshipDao(JPAManager jpaManager, MetamodelProvider metamodelProvider, JpaDataDao dataDao) {
        super(jpaManager);
        this.implementationClasses = metamodelProvider
                .getAllImplementationClasses()
                .stream()
                .filter(Relationship.class::isAssignableFrom)
                .collect(Collectors.toSet());
        this.dataDao = dataDao;
    }

    @Override
    public Optional<Relationship> findById(UUID id) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<RelationshipImpl> query = builder.createQuery(RelationshipImpl.class);
            Root<RelationshipImpl> root = query.from(RelationshipImpl.class);
            query.select(root).where(builder.and(
                    builder.equal(root.get(RelationshipImpl_.identifier), id),
                    getTypeExpression(builder, root)
            ));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }

    @Override
    public List<Relationship> findAll() {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<RelationshipImpl> query = builder.createQuery(RelationshipImpl.class);
            Root<RelationshipImpl> root = query.from(RelationshipImpl.class);
            query.select(root).where(getTypeExpression(builder, root));
            return em.createQuery(query).getResultStream().map(o -> (Relationship) o).collect(Collectors.toList());
        });
    }

    @Override
    public List<Relationship> findAll(@Nullable UUID after, @Nullable UUID before, int maxResults) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<RelationshipImpl> query = builder.createQuery(RelationshipImpl.class);
            Root<RelationshipImpl> root = query.from(RelationshipImpl.class);
            query.select(root);
            Expression<Boolean> where = getTypeExpression(builder, root);
            Paginated<TypedQuery<RelationshipImpl>> paginated = paginateQuery(after, before, maxResults, query, builder, em, root.get(RelationshipImpl_.identifier), where);
            List<Relationship> result = paginated.get()
                    .getResultStream()
                    .map(o -> (Relationship) o)
                    .collect(Collectors.toList());
            if (paginated.isReversed()) {
                Collections.reverse(result);
            }
            return result;
        });
    }

    @Override
    public void deleteAll() {
        jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaDelete<RelationshipImpl> query = builder.createCriteriaDelete(RelationshipImpl.class);
            Root<RelationshipImpl> root = query.from(RelationshipImpl.class);
            query.where(getTypeExpression(builder, root));
            return ((Stream<?>) em.createQuery(query).getResultStream()).map(o -> (Relationship) o).collect(Collectors.toList());
        });
    }

    @Override
    public List<Relationship> findAllByCommitRelatedElement(Commit commit, Element relatedElement, RelationshipDirection direction, @Nullable UUID after, @Nullable UUID before, int maxResults) {
        return jpaManager.transact(em -> {
            // Reverting to non-relational streaming
            // TODO Commit is detached at this point. This ternary mitigates by requerying for the Commit in this transaction. A better solution would be moving transaction handling up to service layer (supported by general wisdom) and optionally migrating to using Play's @Transactional/JPAApi. Pros would include removal of repetitive transaction handling at the DAO layer and ability to interface with multiple DAOs in the same transaction (consistent view). Cons include increased temptation to keep transaction open for longer than needed, e.g. during JSON serialization due to the convenience of @Transactional (deprecated in >= 2.8.x), and the service, a higher level of abstraction, becoming aware of transactions. An alternative would be DAO-to-DAO calls (generally discouraged) and delegating to non-transactional versions of methods.
            Commit c = em.contains(commit) ? commit : em.find(CommitImpl.class, commit.getId());
            Stream<Relationship> stream = dataDao.getCommitIndex(c, em).getWorkingDataVersions().stream()
                    .map(DataVersion::getPayload).filter(data -> data instanceof Relationship)
                    .map(data -> (Relationship) data)
                    .filter(relationship -> {
                                final Stream<? extends Element> related;
                                switch (direction) {
                                    case IN:
                                        related = relationship.getTarget().stream();
                                        break;
                                    case OUT:
                                        related = relationship.getSource().stream();
                                        break;
                                    case BOTH:
                                        related = Stream.concat(relationship.getSource().stream(), relationship.getTarget().stream());
                                        break;
                                    default:
                                        throw new IllegalArgumentException("Unknown RelationshipDirection provided: " + direction.name());
                                }
                                return related
                                        .map(Element::getIdentifier)
                                        .anyMatch(id -> id.equals(relatedElement.getIdentifier()));
                            }
                    );
            Paginated<Stream<Relationship>> paginatedStream = paginateStream(after, before, maxResults, stream, Relationship::getIdentifier);
            List<Relationship> result = paginatedStream.get()
                    .map(JpaElementDao.PROXY_RESOLVER)
                    .map(data -> (Relationship) data)
                    .collect(Collectors.toList());
            if (paginatedStream.isReversed()) {
                Collections.reverse(result);
            }
            return result;
        });
    }

    private Expression<Boolean> getTypeExpression(CriteriaBuilder builder, Root<?> root) {
        return builder.or(implementationClasses.stream()
                .map(c -> builder.equal(root.type(), c))
                .toArray(Predicate[]::new)
        );
    }
}
