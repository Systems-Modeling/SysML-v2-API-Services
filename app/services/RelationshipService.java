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

package services;


import dao.CommitDao;
import dao.ElementDao;
import dao.ProjectDao;
import dao.RelationshipDao;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.metamodel.Element;
import org.omg.sysml.metamodel.Relationship;
import org.omg.sysml.util.RelationshipDirection;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class RelationshipService extends BaseService<Relationship, RelationshipDao> {

    private final ElementDao elementDao;
    private final ProjectDao projectDao;
    private final CommitDao commitDao;

    @Inject
    public RelationshipService(RelationshipDao relationshipDao, ElementDao elementDao, ProjectDao projectDao, CommitDao commitDao) {
        super(relationshipDao);
        this.elementDao = elementDao;
        this.projectDao = projectDao;
        this.commitDao = commitDao;
    }

    public Optional<Relationship> create(Relationship relationship) {
        return relationship.getElementId() != null ? dao.update(relationship) : dao.persist(relationship);
    }

    public List<Relationship> getRelationshipsByProjectCommitRelatedElement(UUID projectId, UUID commitId, UUID relatedElementId, RelationshipDirection direction, boolean excludeUsed, @Nullable UUID after, @Nullable UUID before, int maxResults) {
        Commit commit = projectDao.findById(projectId).flatMap(project -> commitDao.findByProjectAndId(project, commitId)).orElseThrow(() -> new IllegalArgumentException("Commit " + commitId + " not found"));
        Element relatedElement = elementDao.findByCommitAndId(commit, relatedElementId, excludeUsed).orElseThrow(() -> new IllegalArgumentException("Element " + relatedElementId + " not found"));
        return dao.findAllByCommitRelatedElement(commit, relatedElement, direction, excludeUsed, after, before, maxResults);
    }
}
