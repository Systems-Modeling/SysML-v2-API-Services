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

package org.omg.sysml.internal.impl;

import org.omg.sysml.internal.CommitDataVersionIndex;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.DataVersion;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.DataVersionImpl;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity(name = "CommitIndex")
public class CommitDataVersionIndexImpl implements CommitDataVersionIndex {
    private UUID id;
    private Commit commit;
    private Set<DataVersion> workingDataVersion;

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    @JoinColumn(name = "id")
    @OneToOne(targetEntity = CommitImpl.class)
    @MapsId
    public Commit getCommit() {
        return commit;
    }

    @Override
    public void setCommit(Commit commit) {
        this.commit = commit;
    }

    @Override
    @ManyToMany(targetEntity = DataVersionImpl.class, fetch = FetchType.LAZY)
    public Set<DataVersion> getWorkingDataVersion() {
        return workingDataVersion;
    }

    @Override
    public void setWorkingDataVersion(Set<DataVersion> workingDataVersion) {
        this.workingDataVersion = workingDataVersion;
    }
}
