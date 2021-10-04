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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.omg.sysml.lifecycle.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "DataImpl")
@Table(name = "Data")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 63)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public abstract class DataImpl implements Data {

    private UUID key;

    @Id
    @GeneratedValue(generator = "UseExistingOrGenerateUUIDGenerator")
    @Column(name = "key")
    @JsonIgnore
    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }
}
