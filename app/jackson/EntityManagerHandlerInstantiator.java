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

import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;

import javax.persistence.EntityManager;
import java.lang.reflect.Constructor;

public class EntityManagerHandlerInstantiator extends HandlerInstantiator {
    private final EntityManager entityManager;

    public EntityManagerHandlerInstantiator(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public JsonDeserializer<?> deserializerInstance(DeserializationConfig config, Annotated annotated, Class<?> deserClass) {
        try {
            Constructor<?> constructor = deserClass.getConstructor(EntityManager.class);
            if (constructor != null) {
                return (JsonDeserializer<?>) constructor.newInstance(entityManager);
            }
        } catch (ReflectiveOperationException ignored) {
        }
        try {
            return (JsonDeserializer<?>) deserClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException  e) {
            throw new RuntimeException("Failed to build the JsonDeserializer", e);
        }
    }

    @Override
    public KeyDeserializer keyDeserializerInstance(DeserializationConfig config, Annotated annotated, Class<?> keyDeserClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public JsonSerializer<?> serializerInstance(SerializationConfig config, Annotated annotated, Class<?> serClass) {
        try {
            return (JsonSerializer<?>) serClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to build the JsonSerializer", e);
        }
    }

    @Override
    public TypeResolverBuilder<?> typeResolverBuilderInstance(MapperConfig<?> config, Annotated annotated, Class<?> builderClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TypeIdResolver typeIdResolverInstance(MapperConfig<?> config, Annotated annotated, Class<?> resolverClass) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ObjectIdResolver resolverIdGeneratorInstance(MapperConfig<?> config, Annotated annotated, Class<?> implClass) {
        try {
            Constructor<?> constructor = implClass.getConstructor(EntityManager.class);
            if (constructor != null) {
                return (ObjectIdResolver) constructor.newInstance(entityManager);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to build the ObjectIdResolver", e);
        }
        return null;
    }
}
