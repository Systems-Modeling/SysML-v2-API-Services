/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020  InterCAX LLC
 * Copyright (C) 2020  California Institute of Technology ("Caltech")
 * Copyright (C) 2023  Twingineer LLC
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

package org.omg.sysml.query.impl;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import org.omg.sysml.query.PrimitiveConstraint;
import org.omg.sysml.query.PrimitiveOperator;
import play.libs.Json;

import javax.persistence.*;

// @Embeddable // Can't embed and have polymorphism between Constraints, which is necessary for CompositeConstraint.constraint
@Entity(name = "PrimitiveConstraint")
@DiscriminatorValue(value = "PrimitiveConstraint")
@JsonTypeName(value = "PrimitiveConstraint")
public class PrimitiveConstraintImpl extends ConstraintImpl implements PrimitiveConstraint {
    private Boolean inverse;
    private String property;
    private String value;
    private PrimitiveOperator operator;

    @Override
    @Column
    public Boolean getInverse() {
        if (inverse == null) {
            inverse = false;
        }
        return inverse;
    }

    @Override
    public void setInverse(Boolean inverse) {
        this.inverse = inverse;
    }

    @Override
    @Column
    public String getProperty() {
        return property;
    }

    @Override
    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    @Column
    @JsonRawValue
    public String getValue() {
        return value != null ? value : "null";
    }

    @JsonDeserialize(using = OpenJsonNodeDeserializer.class)
    public void setValue(JsonNode value) throws JsonProcessingException {
        this.value = value != null ? Json.mapper().writeValueAsString(value) : "null";
    }

    @Override
    @Enumerated(EnumType.STRING)
    public PrimitiveOperator getOperator() {
        if (operator == null) {
            operator = PrimitiveOperator.EQUALS;
        }
        return operator;
    }

    @Override
    public void setOperator(PrimitiveOperator operator) {
        this.operator = operator;
    }

    public static final class OpenJsonNodeDeserializer extends JsonNodeDeserializer {
        public OpenJsonNodeDeserializer() {
            super();
        }
    }
}
