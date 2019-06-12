package jackson.databind.impl;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import config.MetamodelProvider;
import jackson.EntityManagerHandlerInstantiator;
import jackson.databind.ObjectMapperFactory;
import jpa.manager.JPAManager;
import org.hibernate.SessionFactory;
import play.libs.Json;

import javax.inject.Inject;
import javax.inject.Singleton;

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
        objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);

        Hibernate5Module hibernate5Module = new Hibernate5Module(jpaManager.getEntityManagerFactory().unwrap(SessionFactory.class));
        hibernate5Module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        hibernate5Module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        objectMapper.registerModule(hibernate5Module);

        //See point #5 of https://blog.lahteenmaki.net/making-jackson-tolerable.html
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        objectMapper.setHandlerInstantiator(new EntityManagerHandlerInstantiator(jpaManager.getEntityManagerFactory().createEntityManager()));
        SimpleModule module = new SimpleModule("InterfaceMapping", Version.unknownVersion());
        SimpleAbstractTypeResolver resolver = new SimpleAbstractTypeResolver() {
            {
                for (Class<?> clazz : metamodelProvider.getAllImplementationClasses()) {
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
