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
import org.omg.sysml.metamodel.Element;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class ElementService extends BaseService<Element, ElementDao> {

    private final ProjectDao projectDao;
    private final CommitDao commitDao;

    @Inject
    public ElementService(ElementDao elementDao, ProjectDao projectDao, CommitDao commitDao) {
        super(elementDao);
        this.projectDao = projectDao;
        this.commitDao = commitDao;
    }

    public Optional<Element> create(Element element) {
        return element.getElementId() != null ? dao.update(element) : dao.persist(element);
    }

    public Optional<Element> getElementByCommitIdElementId(UUID commitId, UUID elementId) {
        return commitDao.findById(commitId)
                .flatMap(m -> dao.findByCommitAndId(m, elementId));
    }

    public List<Element> getElementsByProjectIdCommitId(UUID projectId, UUID commitId, @Nullable UUID after, @Nullable UUID before, int maxResults) {
        return projectDao.findById(projectId)
                .flatMap(project -> commitDao.findByProjectAndId(project, commitId))
                .map(commit -> dao.findAllByCommit(commit, after, before, maxResults))
                .orElse(Collections.emptyList());
    }

    public Optional<Element> getElementByProjectIdCommitIdElementId(UUID projectId, UUID commitId, UUID elementId) {
        return projectDao.findById(projectId)
                .flatMap(project -> commitDao.findByProjectAndId(project, commitId))
                .flatMap(commit -> dao.findByCommitAndId(commit, elementId));
    }

    public List<Element> getRootsByProjectIdCommitId(UUID projectId, UUID commitId, @Nullable UUID after, @Nullable UUID before, int maxResults) {
        return projectDao.findById(projectId)
                .flatMap(project -> commitDao.findByProjectAndId(project, commitId))
                .map(commit -> dao.findRootsByCommit(commit, after, before, maxResults))
                .orElse(Collections.emptyList());
    }

    public Optional<Element> getElementByProjectIdCommitIdQualifiedName(UUID projectId, UUID commitId, String qualifiedName) {
        return projectDao.findById(projectId)
                .flatMap(project -> commitDao.findByProjectAndId(project, commitId))
                .flatMap(commit -> dao.findByCommitAndQualifiedName(commit, qualifiedName));
    }
}
