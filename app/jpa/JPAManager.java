package jpa;

import javax.persistence.EntityManager;
import java.util.function.Consumer;
import java.util.function.Function;

public interface JPAManager {
    <R> R transact(Function<EntityManager, R> function);

    void transact(Consumer<EntityManager> consumer);

    String getPersistenceUnitName();

    EntityManager createEntityManager();
}
