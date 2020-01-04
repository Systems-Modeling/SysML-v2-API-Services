package services;


import dao.CommitDao;
import dao.ElementDao;
import dao.ProjectDao;
import dao.RelationshipDao;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;

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

    public List<Relationship> getByRelatedElementId(UUID elementId) {
        return elementDao.findById(elementId).map(e -> relationshipDao.findAllByRelatedElement(e)).orElse(Collections.emptyList());
    }

    public List<Relationship> getBySourceElementId(UUID elementId) {
        return elementDao.findById(elementId).map(e -> relationshipDao.findAllBySourceElement(e)).orElse(Collections.emptyList());
    }

    public List<Relationship> getByTargetElementId(UUID elementId) {
        return elementDao.findById(elementId).map(e -> relationshipDao.findAllByTargetElement(e)).orElse(Collections.emptyList());
    }

    public List<Relationship> getByProjectId(UUID projectId) {
        return projectDao.findById(projectId).map(m -> relationshipDao.findAllByProject(m)).orElse(Collections.emptyList());
    }

    public Set<Relationship> getRelationshipsByProjectCommitRelatedElement(UUID projectId, UUID commitId, UUID relatedElementId) {
        Commit commit = projectDao.findById(projectId).flatMap(project -> commitDao.findByProjectAndId(project, commitId)).orElseThrow(() -> new IllegalArgumentException("Commit " + commitId + " not found."));
        Element relatedElement = elementDao.findByCommitAndId(commit, relatedElementId).orElseThrow(() -> new IllegalArgumentException("Element " + relatedElementId + " not found."));
        return relationshipDao.findAllByCommitRelatedElement(commit, relatedElement);
    }
}
