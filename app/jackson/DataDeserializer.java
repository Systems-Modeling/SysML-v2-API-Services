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

package jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import javassist.util.proxy.ProxyFactory;
import org.omg.sysml.lifecycle.Data;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataDeserializer extends StdDeserializer<Data> implements ContextualDeserializer {
    private static Map<Class<?>, Class<?>> PROXY_MAP = new HashMap<>();
    private EntityManager entityManager;
    private JavaType type;

    public DataDeserializer(EntityManager entityManager) {
        this(entityManager, null);
    }

    public DataDeserializer(EntityManager entityManager, JavaType type) {
        super((Class<?>) null);
        this.entityManager = entityManager;
        this.type = type;
    }

    public DataDeserializer() {
        this(null);
    }

    @Override
    public Data deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() != JsonToken.START_OBJECT) {
            throw new JsonParseException(p, "Expected START_OBJECT. Received " + p.getCurrentName() + ".");
        }

        Data data;
        // Proxy class to handle abstract classes
        Class<?> proxyClass = PROXY_MAP.computeIfAbsent(this.type.getRawClass(), clazz -> {
            ProxyFactory factory = new ProxyFactory();
            factory.setSuperclass(clazz);
            return factory.createClass();
        });
        try {
            data = (Data) proxyClass.getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IOException(e);
        }

        JsonToken token;
        while ((token = p.nextToken()) != null && token != JsonToken.END_OBJECT) {
            if (token == JsonToken.FIELD_NAME && "@id".equals(p.getCurrentName())) {
                p.nextToken();
                data.setId(UUID.fromString(p.getText()));
            }
        }
        return data;
    }

    @Override
    public Object deserializeWithType(
            JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        //beanProperty is null when the type to deserialize is the top-level type or a generic type, not a type of a bean property
        JavaType type = ctxt.getContextualType() != null ? ctxt.getContextualType() : property.getMember().getType();
        return new DataDeserializer(entityManager, type);
    }
}