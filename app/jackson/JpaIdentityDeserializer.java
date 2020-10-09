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

package jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import javax.persistence.EntityManager;
import java.io.IOException;

public abstract class JpaIdentityDeserializer<T> extends StdDeserializer<T> {
    private EntityManager entityManager;

    public JpaIdentityDeserializer(EntityManager entityManager) {
        super((Class<?>) null);
        this.entityManager = entityManager;
    }

    public JpaIdentityDeserializer() {
        this(null);
    }

    protected abstract boolean isIdentityField(String field);

    protected abstract T deserializeFromIdentity(JsonParser parser) throws IOException;

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() != JsonToken.START_OBJECT) {
            throw new JsonParseException(p, "Expected START_OBJECT. Received " + p.getCurrentName() + ".");
        }
        JsonToken token;
        while ((token = p.nextToken()) != null && token != JsonToken.END_OBJECT) {
            if (token == JsonToken.FIELD_NAME && isIdentityField(p.getCurrentName())) {
                if (p.nextToken() == null) {
                    throw new JsonParseException(p, "No value for identity field.");
                }
                return deserializeFromIdentity(p);
            }
        }
        return null;
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}