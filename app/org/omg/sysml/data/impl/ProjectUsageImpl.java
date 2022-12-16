/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020 InterCAX LLC
 * Copyright (C) 2020 California Institute of Technology ("Caltech")
 * Copyright (C) 2021-2022 Twingineer LLC
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

package org.omg.sysml.data.impl;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.RecordSerialization;
import org.hibernate.annotations.FetchMode;
import org.omg.sysml.data.ProjectUsage;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.impl.CommitImpl;
import org.omg.sysml.lifecycle.impl.DataImpl;
import org.omg.sysml.lifecycle.impl.ProjectImpl;

import javax.persistence.*;
import java.util.UUID;

import static jackson.RecordSerialization.IDENTITY_FIELD;

@Entity(name = "ProjectUsageImpl")
@SecondaryTable(name = "ProjectUsage")
@org.hibernate.annotations.Table(appliesTo = "ProjectUsage", fetch = FetchMode.SELECT, optional = false)
@DiscriminatorValue(value = "ProjectUsage")
@JsonTypeName(value = "ProjectUsage")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class ProjectUsageImpl extends DataImpl implements ProjectUsage {

    private UUID id;

    @Column(name = "id", table = "ProjectUsage")
    @JsonGetter(value = IDENTITY_FIELD)
    public UUID getId() {
        return id;
    }

    @JsonSetter(value = IDENTITY_FIELD)
    public void setId(UUID id) {
        this.id = id;
    }

    private Commit usedCommit;

    @JsonGetter
    @JsonSerialize(using = RecordSerialization.RecordSerializer.class)
    @ManyToOne(targetEntity = CommitImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "usedCommitId", table = "ProjectUsage")
    public Commit getUsedCommit() {
        return usedCommit;
    }

    @JsonSetter
    @JsonDeserialize(as = CommitImpl.class, using = RecordSerialization.CommitDeserializer.class)
    public void setUsedCommit(Commit usedCommit) {
        this.usedCommit = usedCommit;
    }

    @Override
    @Transient
    @JsonSerialize(as = ProjectImpl.class, using = RecordSerialization.RecordSerializer.class)
    public Project getUsedProject() {
        Commit usedCommit = getUsedCommit();
        if (usedCommit == null) {
            return null;
        }
        return usedCommit.getOwningProject();
    }

    @Transient
    @JsonProperty("@type")
    public String getType() {
        return ProjectUsage.NAME;
    }
}
