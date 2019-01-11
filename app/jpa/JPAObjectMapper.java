package jpa;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.EntityManagerHandlerInstantiator;
import models.Element;
import models.Model;
import models.Relationship;
import play.libs.Json;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JPAObjectMapper extends ObjectMapper {
    @Inject
    public JPAObjectMapper(KunderaManager jpa) {
        //See point #5 of https://blog.lahteenmaki.net/making-jackson-tolerable.html
        configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        setHandlerInstantiator(new EntityManagerHandlerInstantiator(jpa.createEntityManager()));
        registerSubtypes(Element.class, Relationship.class, Model.class);
        Json.setObjectMapper(this);
    }
}
