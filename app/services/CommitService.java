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

import dao.BranchDao;
import dao.CommitDao;
import dao.ProjectDao;
import org.omg.sysml.lifecycle.Branch;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Project;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;

@Singleton
public class CommitService extends BaseService<Commit, CommitDao> {

    private final ProjectDao projectDao;
    private final BranchDao branchDao;

    @Inject
    public CommitService(CommitDao commitDao, ProjectDao projectDao, BranchDao branchDao) {
        super(commitDao);
        this.projectDao = projectDao;
        this.branchDao = branchDao;
    }

    public Optional<Commit> create(UUID projectId, UUID branchId, Commit commit) {
        return create(projectId, branchId, commit, dao::persist);
    }

    public Optional<Commit> createNameResolved(UUID projectId, UUID branchId, Commit commit) {
        return create(projectId, branchId, commit, dao::persistNameResolved);
    }

    private Optional<Commit> create(UUID projectId, UUID branchId, Commit commit, BiFunction<Commit, Branch, Optional<Commit>> persister) {
        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project " + projectId + " not found"));
        commit.setOwningProject(project);
        Branch branch = branchId != null ?
                branchDao.findByProjectAndId(project, branchId)
                        .orElseThrow(() -> new IllegalArgumentException("Branch " + branchId + " not found")) :
                Optional.ofNullable(project.getDefaultBranch())
                        .orElseThrow(() -> new IllegalStateException("Branch not specified and project does not have default branch"));
        return persister.apply(commit, branch);
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
}
