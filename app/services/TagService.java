/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2021 Twingineer LLC
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

import dao.ProjectDao;
import dao.TagDao;
import org.omg.sysml.lifecycle.Tag;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class TagService extends BaseService<Tag, TagDao> {

    private final ProjectDao projectDao;

    @Inject
    public TagService(TagDao tagDao, ProjectDao projectDao) {
        super(tagDao);
        this.projectDao = projectDao;
    }

    public Optional<Tag> create(Tag tag) {
        if (tag.getName() == null || tag.getName().isBlank()) {
            throw new IllegalArgumentException("Tag must have name");
        }
        if (tag.getTaggedCommit() == null) {
            throw new IllegalArgumentException("Tag must have taggedCommit");
        }
        return dao.persist(tag);
    }

    public Optional<Tag> create(UUID projectId, Tag tag) {
        tag.setOwningProject(projectDao.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project " + projectId + " not found")));
        return create(tag);
    }

    public List<Tag> getByProjectId(UUID projectId, UUID after, UUID before, int maxResults) {
        return projectDao.findById(projectId)
                .map(project -> dao.findAllByProject(project, after, before, maxResults))
                .orElse(Collections.emptyList());
    }

    public Optional<Tag> getByProjectIdAndId(UUID projectId, UUID branchId) {
        return projectDao.findById(projectId)
                .flatMap(project -> dao.findByProjectAndId(project, branchId));
    }

    public Optional<Tag> deleteByProjectIdAndId(UUID projectId, UUID branchId) {
        return projectDao.findById(projectId)
                .flatMap(proj -> dao.deleteByProjectAndId(proj, branchId));
    }
}
