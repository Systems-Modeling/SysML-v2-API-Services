package kundera;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jackson.EntityManagerHandlerInstantiator;
import models.Element;
import models.Model;
import models.Relationship;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

@Singleton
public class JPAManagerImpl implements JPAManager {
    private static final String DEFAULT_PERSISTENCE_UNIT_NAME = "cassandra";
    private final String persistenceUnitName;
    private final EntityManager entityManager;
    private ObjectMapper objectMapper;

    @Inject
    public JPAManagerImpl() {
        this(DEFAULT_PERSISTENCE_UNIT_NAME);
    }

    public JPAManagerImpl(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
        try {
            Map<String, Object> kunderaConfiguration = new HashMap<>();
            Properties systemProperties = System.getProperties();
            for (String propertyName : systemProperties.stringPropertyNames()) {
                if (propertyName.startsWith("kundera.")) {
                    kunderaConfiguration.put(propertyName, systemProperties.getProperty(propertyName));
                }
            }
            entityManager = Persistence.createEntityManagerFactory(persistenceUnitName, kunderaConfiguration).createEntityManager();
            /*
            Do not hardcode the CQL version to 3.0.0 unless you know *exactly* what you're doing.
            entityManager.setProperty("cql.version", "3.0.0");
            */
        } catch (Throwable t) {
            System.err.println("Building EntityManager failed.");
            throw new ExceptionInInitializerError(t);
        }
    }

    @Override
    public <R> R transact(Function<EntityManager, R> function) {
        return function.apply(entityManager);
    }

    @Override
    public void transact(Consumer<EntityManager> consumer) {
        consumer.accept(entityManager);
    }

    @Override
    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    @Override
    public ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            //See point #5 of https://blog.lahteenmaki.net/making-jackson-tolerable.html
            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
            objectMapper.setHandlerInstantiator(new EntityManagerHandlerInstantiator(entityManager));
            objectMapper.registerSubtypes(Element.class, Relationship.class, Model.class);
        }
        return objectMapper;
    }
}
