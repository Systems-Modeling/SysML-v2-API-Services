package jpa;

import com.impetus.kundera.persistence.EntityManagerFactoryImpl;

import javax.persistence.EntityManagerFactory;

public class Dialects {
    public static boolean isDialectCassandra(EntityManagerFactory emf, String persistenceUnit) {
        return "cassandra".equals(getDialect(emf, persistenceUnit));
    }

    public static String getDialect(EntityManagerFactory emf, String persistenceUnit) {
        if (emf instanceof EntityManagerFactoryImpl) {
            return getDialectKundera((EntityManagerFactoryImpl) emf, persistenceUnit);
        }
        else if (emf.getProperties().containsKey("hibernate.dialect")) {
            return getDialectHibernate(emf);
        }
        throw new RuntimeException("Unable to detect the dialect in use by the persistence unit \"" + persistenceUnit + "\".");
    }

    private static String getDialectKundera(EntityManagerFactoryImpl emfi, String persistenceUnit) {
        return emfi.getKunderaMetadataInstance().getApplicationMetadata().getPersistenceUnitMetadata(persistenceUnit).getProperty("kundera.dialect");
    }

    private static String getDialectHibernate(EntityManagerFactory emf) {
        return (String) emf.getProperties().get("hibernate.dialect");
    }
}
