/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020 InterCAX LLC
 * Copyright (C) 2020 California Institute of Technology ("Caltech")
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

package dao;

import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.metamodel.Element;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ElementDao extends Dao<Element> {

    List<Element> findAllByCommit(Commit commit, boolean excludeUsed, @Nullable UUID after, @Nullable UUID before, int maxResults);

    Optional<Element> findByCommitAndId(Commit commit, UUID id, boolean excludeUsed);

    List<Element> findRootsByCommit(Commit commit, boolean excludeUsed, @Nullable UUID after, @Nullable UUID before, int maxResults);

    Optional<Element> findByCommitAndQualifiedName(Commit commit, String qualifiedName);
}
