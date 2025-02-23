/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020  InterCAX LLC
 * Copyright (C) 2020  California Institute of Technology ("Caltech")
 * Copyright (C) 2023-2025  Twingineer LLC
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

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javabean.JavaBeanHelper;
import org.omg.sysml.query.PrimitiveConstraint;
import org.omg.sysml.query.PrimitiveOperator;
import play.libs.Json;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

// @Embeddable // Can't embed and have polymorphism between Constraints, which is necessary for CompositeConstraint.constraint
@Entity(name = "PrimitiveConstraint")
@DiscriminatorValue(value = "PrimitiveConstraint")
@JsonTypeName(value = "PrimitiveConstraint")
public class PrimitiveConstraintImpl extends ConstraintImpl implements PrimitiveConstraint {
    private Boolean inverse;
    private String property;
    private List<String> value;
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
    @JsonProperty(required = true)
    public String getProperty() {
        return property;
    }

    @Override
    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    @JsonProperty(required = true)
    @JsonSerialize(contentUsing = RawValueSerializer.class)
    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.TextType")
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable
    public @Nullable List<String> getValue() {
        return value.isEmpty() ? null : value;
    }

    public void setValue(@Nullable List<String> value) {
        this.value = value == null ? Collections.emptyList() : value;
    }

    @JsonSetter("value")
    @JsonDeserialize(contentUsing = OpenJsonNodeDeserializer.class)
    public void setValueJson(@Nullable List<JsonNode> value) throws JsonProcessingException {
        if (value == null) {
            this.value = Collections.emptyList();
            return;
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException("value must not be empty");
        }

        List<String> result = new ArrayList<>(value.size());
        for (JsonNode node : value) {
            if (node.isBoolean() || node.isNumber() || node.isTextual()) {
                result.add(Json.mapper().writeValueAsString(node));
            } else if (node.isObject()) {
                if (node.size() != 1) {
                    throwBadValue();
                }
                if (extractId(node) == null) {
                    throwBadValue();
                }
                result.add(Json.mapper().writeValueAsString(node));
            } else {
                throwBadValue();
            }
        }
        this.value = result;
    }

    @Override
    @Enumerated(EnumType.STRING)
    @JsonProperty(required = true)
    public PrimitiveOperator getOperator() {
        return operator;
    }

    @Override
    public void setOperator(PrimitiveOperator operator) {
        this.operator = operator;
    }

    public static @Nullable UUID extractId(JsonNode node) {
        return JavaBeanHelper.convert(
                // intentionally `textValue` instead of `asText` to get a null value for improved error reporting
                node.path("@id").textValue(),
                UUID.class
        );
    }

    private static void throwBadValue() {
        throw new IllegalArgumentException("value must be typed boolean, number, string, or Identified");
    }

    public static final class RawValueSerializer extends StdSerializer<String> {
        public RawValueSerializer() {
            super(String.class);
        }

        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeRawValue(value);
        }
    }

    public static final class OpenJsonNodeDeserializer extends JsonNodeDeserializer {
        public OpenJsonNodeDeserializer() {
            super();
        }
    }
}
