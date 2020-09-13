package dao.impl.jpa;

import dao.QueryDao;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.query.Query;
import org.omg.sysml.query.impl.QueryImpl;
import org.omg.sysml.query.impl.QueryImpl_;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class JpaQueryDao extends SimpleJpaDao<Query, QueryImpl> implements QueryDao {

    @Inject
    public JpaQueryDao(JPAManager jpaManager) {
        super(jpaManager, QueryImpl.class, QueryImpl_.id);
    }

    @Override
    public Optional<Query> persist(Query query) {
        return jpaManager.transact(em -> {
            query.setScope(
                    query.getScope().stream()
                            .filter(identity -> Objects.nonNull(identity.getId()))
                            .map(em::merge)
                            .collect(Collectors.toSet())
            );
            return super.persist(query, em);
        });
    }

    @Override
    public List<Query> findAllByProject(Project project) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<QueryImpl> query = builder.createQuery(QueryImpl.class);
            Root<QueryImpl> root = query.from(QueryImpl.class);
            query.select(root)
                    .where(builder.equal(root.get(QueryImpl_.containingProject), project));
            return em.createQuery(query).getResultStream().collect(Collectors.toList());
        });
    }

    @Override
    public Optional<Query> findByProjectAndId(Project project, UUID id) {
        return jpaManager.transact(em -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<QueryImpl> query = builder.createQuery(QueryImpl.class);
            Root<QueryImpl> root = query.from(QueryImpl.class);
            query.select(root)
                    .where(builder.and(
                            builder.equal(root.get(QueryImpl_.containingProject), project),
                            builder.equal(root.get(QueryImpl_.id), id)
                    ));
            try {
                return Optional.of(em.createQuery(query).getSingleResult());
            } catch (NoResultException e) {
                return Optional.empty();
            }
        });
    }
}
