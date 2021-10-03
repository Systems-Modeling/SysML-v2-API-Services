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

package org.omg.sysml.lifecycle.impl;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.RecordSerialization;
import org.omg.sysml.lifecycle.Commit;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.lifecycle.Tag;
import org.omg.sysml.record.impl.RecordImpl;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity(name = "Tag")
public class TagImpl extends RecordImpl implements Tag {
    private Project owningProject;
    private Commit taggedCommit;
    private String name;
    private ZonedDateTime timestamp;

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

    @ManyToOne(targetEntity = CommitImpl.class, fetch = FetchType.LAZY)
    @JsonSerialize(as = CommitImpl.class, using = RecordSerialization.RecordSerializer.class)
    public Commit getTaggedCommit() {
        return taggedCommit;
    }

    @JsonDeserialize(as = CommitImpl.class, using = RecordSerialization.CommitDeserializer.class)
    public void setTaggedCommit(Commit head) {
        this.taggedCommit = head;
    }

    @Override
    @Transient
    @JsonSerialize(as = CommitImpl.class, using = RecordSerialization.RecordSerializer.class)
    public Commit getReferencedCommit() {
        return Tag.super.getReferencedCommit();
    }

    @JsonProperty(required = true)
    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @Column(name = "name", table = "Tag")
    public String getName() {
        return name;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }

    @Override
    @Column
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Transient
    @JsonProperty("@type")
    public String getType() {
        return Tag.NAME;
    }
}
