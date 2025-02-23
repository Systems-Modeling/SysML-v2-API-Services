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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jackson.RecordSerialization;
import org.omg.sysml.lifecycle.Branch;
import org.omg.sysml.lifecycle.Project;
import org.omg.sysml.record.impl.RecordImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity(name = "Project")
public class ProjectImpl extends RecordImpl implements Project {

    private ZonedDateTime created;

    @Override
    @Column
    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    @Override
    @JsonProperty(required = true)
    public @NotNull String getName() {
        return super.getName();
    }

    @Override
    @JsonProperty(required = true)
    public void setName(@NotNull String name) {
        super.setName(name);
    }

    private Branch defaultBranch;

    @OneToOne(targetEntity = BranchImpl.class, cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonSerialize(as = BranchImpl.class, using = RecordSerialization.RecordSerializer.class)
    public Branch getDefaultBranch() {
        return defaultBranch;
    }

    @JsonDeserialize(as = BranchImpl.class, using = RecordSerialization.BranchDeserializer.class)
    public void setDefaultBranch(Branch defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    private String description;

    @JsonProperty(required = true)
    @JsonGetter
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @javax.persistence.Column(name = "description", table = "Project")
    public String getDescription() {
        return description;
    }

    @JsonProperty(required = true)
    @JsonSetter
    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    @JsonProperty("@type")
    public String getType() {
        return Project.NAME;
    }
}
