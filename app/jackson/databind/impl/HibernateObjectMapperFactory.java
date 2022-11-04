/*
 * SysML v2 REST/HTTP Pilot Implementation
 * Copyright (C) 2020 InterCAX LLC
 * Copyright (C) 2020 California Institute of Technology ("Caltech")
 * Copyright (C) 2022 Twingineer LLC
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

package jackson.databind.impl;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import config.MetamodelProvider;
import jackson.EntityManagerHandlerInstantiator;
import jackson.databind.ObjectMapperFactory;
import jpa.manager.JPAManager;
import org.hibernate.SessionFactory;
import play.libs.Json;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.lang.reflect.Modifier;

@Singleton
public class HibernateObjectMapperFactory implements ObjectMapperFactory {
    private final MetamodelProvider metamodelProvider;
    private final JPAManager jpaManager;

    @Inject
    public HibernateObjectMapperFactory(MetamodelProvider metamodelProvider, JPAManager jpaManager) {
        this.metamodelProvider = metamodelProvider;
        this.jpaManager = jpaManager;
        Json.setObjectMapper(getObjectMapper());
    }

    @Override
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = Json.newDefaultMapper();
        objectMapper.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);

        Hibernate5Module hibernate5Module = new Hibernate5Module(jpaManager.getEntityManagerFactory().unwrap(SessionFactory.class));
        hibernate5Module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        objectMapper.registerModule(hibernate5Module);

        objectMapper.registerModule(new JavaTimeModule());

        //See point #5 of https://blog.lahteenmaki.net/making-jackson-tolerable.html
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        objectMapper.setHandlerInstantiator(new EntityManagerHandlerInstantiator(jpaManager.getEntityManagerFactory().createEntityManager()));
        SimpleModule module = new SimpleModule("InterfaceMapping", Version.unknownVersion());
        SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver() {
            {
                for (Class<?> clazz : metamodelProvider.getAllImplementationClasses()) {
                    if (Modifier.isAbstract(clazz.getModifiers())) {
                        continue;
                    }
                    Class<?> intrface;
                    try {
                        intrface = metamodelProvider.getInterface(clazz);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    _mappings.put(new ClassKey(intrface), clazz);
                    module.registerSubtypes(intrface, clazz);
                    objectMapper.registerSubtypes(intrface, clazz);
                }
            }
        };
        module.setAbstractTypes(resolver);
        //objectMapper.registerModule(module);
        return objectMapper;
    }
}
