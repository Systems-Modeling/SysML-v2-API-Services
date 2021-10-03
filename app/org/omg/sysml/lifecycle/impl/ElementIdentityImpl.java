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
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.omg.sysml.lifecycle.ElementIdentity;
import org.omg.sysml.record.impl.RecordImpl;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity(name = "ElementIdentity")
@JsonTypeName(value = "ElementIdentity")
public class ElementIdentityImpl extends RecordImpl implements ElementIdentity {
    @Transient
    @JsonProperty("@type")
    public String getType() {
        return ElementIdentity.NAME;
    }
}
