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

package org.omg.sysml.lifecycle.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.Any;
import org.omg.sysml.lifecycle.ElementIdentity;
import org.omg.sysml.lifecycle.ElementVersion;
import org.omg.sysml.metamodel.MofObject;
import org.omg.sysml.metamodel.impl.MofObjectImpl;
import org.omg.sysml.record.impl.RecordImpl;

import javax.persistence.*;

@Entity(name = "ElementVersion")
@JsonTypeName(value = "ElementVersion")
public class ElementVersionImpl extends RecordImpl implements ElementVersion {
    private MofObject data;
    private ElementIdentity identity;

    @Any(metaDef = "MofObjectMetaDef", metaColumn = @javax.persistence.Column(name = "dataType"), fetch = FetchType.EAGER)
    @JoinColumn(name = "dataId")
    @org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @JsonDeserialize(as = MofObjectImpl.class)
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
    public MofObject getData() {
        return data;
    }

    public void setData(MofObject data) {
        this.data = data;
    }

    @ManyToOne(targetEntity = ElementIdentityImpl.class, cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonDeserialize(as = ElementIdentityImpl.class)
    public ElementIdentity getIdentity() {
        return identity;
    }

    public void setIdentity(ElementIdentity identity) {
        this.identity = identity;
    }

    @Transient
    @JsonProperty("@type")
    public static String getType() {
        return ElementVersion.class.getSimpleName();
    }
}
