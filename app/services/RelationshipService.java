package services;


import dao.CommitDao;
import dao.ElementDao;
import dao.ProjectDao;
import dao.RelationshipDao;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.utils.RelationshipDirection;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class RelationshipService {
    @Inject
    private RelationshipDao relationshipDao;

    @Inject
    private ElementDao elementDao;

    @Inject
    private ProjectDao projectDao;

    @Inject
    private CommitDao commitDao;

    public List<Relationship> getAll() {
        return relationshipDao.findAll();
    }

    public Optional<Relationship> getById(UUID id) {
        return relationshipDao.findById(id);
    }

    public Optional<Relationship> create(Relationship relationship) {
        return relationship.getIdentifier() != null ? relationshipDao.update(relationship) : relationshipDao.persist(relationship);
    }

    public Set<Relationship> getRelationshipsByProjectCommitRelatedElement(UUID projectId, UUID commitId, UUID relatedElementId, Optional<RelationshipDirection> direction) {
        Commit commit = projectDao.findById(projectId).flatMap(project -> commitDao.findByProjectAndId(project, commitId)).orElseThrow(() -> new IllegalArgumentException("Commit " + commitId + " not found."));
        Element relatedElement = elementDao.findByCommitAndId(commit, relatedElementId).orElseThrow(() -> new IllegalArgumentException("Element " + relatedElementId + " not found."));
        Set<Relationship> allRelationships = relationshipDao.findAllByCommitRelatedElement(commit, relatedElement);
        Set<Relationship> results = allRelationships;
        if (direction.isPresent()) {
            if (direction.get().equals(RelationshipDirection.OUT)) {
                results = allRelationships.stream().filter(r -> r.getSource().stream().anyMatch(e -> e.getIdentifier().equals(relatedElementId))).collect(Collectors.toSet());
            } else if (direction.get().equals(RelationshipDirection.IN)) {
                results = allRelationships.stream().filter(r -> r.getTarget().stream().anyMatch(e -> e.getIdentifier().equals(relatedElementId))).collect(Collectors.toSet());
            }
        }

        return results;
    }
}
