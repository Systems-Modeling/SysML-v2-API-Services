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
import dao.ProjectDao;
import org.omg.sysml.lifecycle.Commit;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class CommitService extends BaseService<Commit, CommitDao> {

    private final ProjectDao projectDao;

    @Inject
    public CommitService(CommitDao commitDao, ProjectDao projectDao) {
        super(commitDao);
        this.projectDao = projectDao;
    }

    public Optional<Commit> create(Commit commit) {
        return commit.getId() != null ? dao.update(commit) : dao.persist(commit);
    }

    public Optional<Commit> create(UUID projectId, Commit commit) {
        commit.setOwningProject(projectDao.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project " + projectId + " not found.")));
        return create(commit);
    }

    public List<Commit> getByProjectId(UUID projectId, UUID after, UUID before, int maxResults) {
        return projectDao.findById(projectId)
                .map(project -> dao.findAllByProject(project, after, before, maxResults))
                .orElse(Collections.emptyList());
    }

    public Optional<Commit> getByProjectIdAndId(UUID projectId, UUID commitId) {
        return projectDao.findById(projectId)
                .flatMap(project -> dao.findByProjectAndIdResolved(project, commitId));
    }

    public Optional<Commit> getHeadByProjectId(UUID projectId) {
        return projectDao.findById(projectId)
                .flatMap(dao::findHeadByProject);
    }
}
