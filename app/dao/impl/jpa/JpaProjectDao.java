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

package dao.impl.jpa;

import dao.ProjectDao;
import jpa.manager.JPAManager;
import org.omg.sysml.lifecycle.Branch;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.impl.BranchImpl;
import org.omg.sysml.lifecycle.impl.ProjectImpl;
import org.omg.sysml.lifecycle.impl.ProjectImpl_;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class JpaProjectDao extends SimpleJpaDao<Project, ProjectImpl> implements ProjectDao {

    @Inject
    public JpaProjectDao(JPAManager jpaManager) {
        super(jpaManager, ProjectImpl.class, ProjectImpl_.id);
    }

    @Override
    public Optional<Project> update(Project project) {
        if (project.getDefaultBranch() == null) {
            throw new IllegalArgumentException("Default branch must be provided");
        }
        return super.update(project);
    }

    @Override
    public Optional<Project> persist(Project project) {
        if (project.getDefaultBranch() != null) {
            throw new IllegalArgumentException("Cannot specify default branch when creating Project");
        }
        Branch defaultBranch = new BranchImpl();
        defaultBranch.setOwningProject(project);
        defaultBranch.setName(Project.DEFAULT_BRANCH_NAME);
        // TODO Add timestamp to Project and inherit into defaultBranch
        defaultBranch.setTimestamp(ZonedDateTime.now());
        project.setDefaultBranch(defaultBranch);
        return super.persist(project);
    }
}
