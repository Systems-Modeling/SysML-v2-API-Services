package jpa;

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
public class KunderaManager implements JPAManager {
    private static final String DEFAULT_PERSISTENCE_UNIT_NAME = "cassandra";
    private final String persistenceUnitName;
    private final EntityManager entityManager;

    @Inject
    public KunderaManager() {
        this(DEFAULT_PERSISTENCE_UNIT_NAME);
    }

    public KunderaManager(String persistenceUnitName) {
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

    public <R> R transact(Function<EntityManager, R> function) {
        return function.apply(getEntityManager());
    }

    public void transact(Consumer<EntityManager> consumer) {
        consumer.accept(getEntityManager());
    }

    public String getPersistenceUnitName() {
        return persistenceUnitName;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
