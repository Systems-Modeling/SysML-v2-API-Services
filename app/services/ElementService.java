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
import dao.QueryDao;
import org.omg.sysml.metamodel.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Singleton
public class ElementService extends BaseService<Element, ElementDao> {

    private final ProjectDao projectDao;
    private final CommitDao commitDao;
    private final QueryDao queryDao;

    @Inject
    public ElementService(ElementDao elementDao, ProjectDao projectDao, CommitDao commitDao, QueryDao queryDao) {
        super(elementDao);
        this.projectDao = projectDao;
        this.commitDao = commitDao;
        this.queryDao = queryDao;
    }

    public Optional<Element> create(Element element) {
        return element.getIdentifier() != null ? dao.update(element) : dao.persist(element);
    }

    public Set<Element> getByCommitId(UUID commitId) {
        return commitDao.findById(commitId)
                .map(dao::findAllByCommit).orElse(Collections.emptySet());
    }

    public Optional<Element> getByCommitIdAndId(UUID commitId, UUID elementId) {
        return commitDao.findById(commitId)
                .flatMap(m -> dao.findByCommitAndId(m, elementId));
    }

    public Set<Element> getElementsByProjectIdCommitId(UUID projectId, UUID commitId) {
        return projectDao.findById(projectId)
                .flatMap(project -> commitDao.findByProjectAndId(project, commitId))
                .map(dao::findAllByCommit)
                .orElse(Collections.emptySet());
    }

    public Optional<Element> getElementsByProjectIdCommitIdElementId(UUID projectId, UUID commitId, UUID elementId) {
        return projectDao.findById(projectId)
                .flatMap(project -> commitDao.findByProjectAndId(project, commitId))
                .flatMap(commit -> dao.findByCommitAndId(commit, elementId));
    }

    public Set<Element> getRootsByProjectIdCommitId(UUID projectId, UUID commitId) {
        return projectDao.findById(projectId)
                .flatMap(project -> commitDao.findByProjectAndId(project, commitId))
                .map(dao::findRootsByCommit)
                .orElse(Collections.emptySet());
    }
}
