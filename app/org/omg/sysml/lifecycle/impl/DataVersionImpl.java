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

package org.omg.sysml.lifecycle.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.Any;
import org.omg.sysml.lifecycle.Data;
import org.omg.sysml.lifecycle.DataIdentity;
import org.omg.sysml.lifecycle.DataVersion;
import org.omg.sysml.record.impl.RecordImpl;

import javax.persistence.*;

@Entity(name = "DataVersionImpl")
@Table(name = "DataVersion")
@JsonTypeName(value = "DataVersion")
public class DataVersionImpl extends RecordImpl implements DataVersion {
    private Data payload;
    private DataIdentity identity;

    @Any(metaDef = "DataMetaDef", metaColumn = @Column(name = "payloadType"), fetch = FetchType.EAGER)
    @JoinColumn(name = "payloadId")
    @org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    public Data getPayload() {
        return payload;
    }

    @JsonDeserialize(as = DataImpl.class)
    public void setPayload(Data data) {
        this.payload = data;
    }

    @ManyToOne(targetEntity = DataIdentityImpl.class, cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    public DataIdentity getIdentity() {
        return identity;
    }

    @JsonDeserialize(as = DataIdentityImpl.class)
    public void setIdentity(DataIdentity identity) {
        this.identity = identity;
    }

    @Transient
    @JsonProperty("@type")
    public static String getType() {
        return DataVersion.NAME;
    }
}
