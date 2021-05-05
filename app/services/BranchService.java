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
import dao.ProjectDao;
import org.omg.sysml.lifecycle.Branch;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class BranchService extends BaseService<Branch, BranchDao> {

    private final ProjectDao projectDao;

    @Inject
    public BranchService(BranchDao branchDao, ProjectDao projectDao) {
        super(branchDao);
        this.projectDao = projectDao;
    }

    public Optional<Branch> create(Branch branch) {
        return branch.getId() != null ? dao.update(branch) : dao.persist(branch);
    }

    public Optional<Branch> create(UUID projectId, Branch branch) {
        branch.setOwningProject(projectDao.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project " + projectId + " not found")));
        return create(branch);
    }

    public List<Branch> getByProjectId(UUID projectId, UUID after, UUID before, int maxResults) {
        return projectDao.findById(projectId)
                .map(project -> dao.findAllByProject(project, after, before, maxResults))
                .orElse(Collections.emptyList());
    }

    public Optional<Branch> getByProjectIdAndId(UUID projectId, UUID branchId) {
        return projectDao.findById(projectId)
                .flatMap(project -> dao.findByProjectAndId(project, branchId));
    }
}
