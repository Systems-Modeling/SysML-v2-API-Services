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

package org.omg.sysml.lifecycle.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.RecordSerialization;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.DataVersion;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.record.impl.RecordImpl;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Commit")
public class CommitImpl extends RecordImpl implements Commit {
    private Project owningProject;
    private Set<DataVersion> change;
    private ZonedDateTime created;
    private String description;
    private Commit previousCommit;

    @Override
    @ManyToOne(targetEntity = ProjectImpl.class, fetch = FetchType.LAZY)
    @JsonSerialize(as = ProjectImpl.class, using = RecordSerialization.RecordSerializer.class)
    public Project getOwningProject() {
        return owningProject;
    }

    @JsonDeserialize(as = ProjectImpl.class, using = RecordSerialization.ProjectDeserializer.class)
    public void setOwningProject(Project owningProject) {
        this.owningProject = owningProject;
    }

    @OneToMany(targetEntity = DataVersionImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<DataVersion> getChange() {
        if (change == null) {
            change = new HashSet<>();
        }
        return change;
    }

    @JsonDeserialize(contentAs = DataVersionImpl.class)
    public void setChange(Set<DataVersion> change) {
        this.change = change;
    }

    @Override
    @Column
    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "description", table = "Commit")
    public String getDescription() {
        return description;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(targetEntity = CommitImpl.class, fetch = FetchType.LAZY)
    @JsonSerialize(as = CommitImpl.class, using = RecordSerialization.RecordSerializer.class)
    public Commit getPreviousCommit() {
        return previousCommit;
    }

    @JsonDeserialize(as = CommitImpl.class, using = RecordSerialization.CommitDeserializer.class)
    public void setPreviousCommit(Commit previousCommit) {
        this.previousCommit = previousCommit;
    }

    @Transient
    @JsonProperty("@type")
    public String getType() {
        return Commit.NAME;
    }
}
