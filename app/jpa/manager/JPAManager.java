package jpa.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Consumer;
import java.util.function.Function;

public interface JPAManager {
    String getPersistenceUnitName();

    EntityManagerFactory getEntityManagerFactory();

    <R> R transact(Function<EntityManager, R> function);

    void transact(Consumer<EntityManager> consumer);
}