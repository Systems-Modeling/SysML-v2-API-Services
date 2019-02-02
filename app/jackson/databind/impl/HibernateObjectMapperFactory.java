package jackson.databind.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import jackson.EntityManagerHandlerInstantiator;
import jackson.databind.ObjectMapperFactory;
import jpa.manager.JPAManager;
import models.Element;
import models.Model;
import models.Relationship;
import org.hibernate.SessionFactory;
import play.libs.Json;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HibernateObjectMapperFactory implements ObjectMapperFactory {
    private final JPAManager jpaManager;

    @Inject
    public HibernateObjectMapperFactory(JPAManager jpaManager) {
        this.jpaManager = jpaManager;
        Json.setObjectMapper(getObjectMapper());
    }

    @Override
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = Json.newDefaultMapper();

        Hibernate5Module hibernate5Module = new Hibernate5Module(jpaManager.getEntityManagerFactory().unwrap(SessionFactory.class));
        hibernate5Module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        objectMapper.registerModule(hibernate5Module);

        //See point #5 of https://blog.lahteenmaki.net/making-jackson-tolerable.html
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        objectMapper.setHandlerInstantiator(new EntityManagerHandlerInstantiator(jpaManager.getEntityManagerFactory().createEntityManager()));
        objectMapper.registerSubtypes(Element.class, Relationship.class, Model.class);
        return objectMapper;
    }
}
