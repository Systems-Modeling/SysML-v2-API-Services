package dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Dao<E> {

    Optional<E> persist(E entity);

    Optional<E> update(E entity);

    Optional<E> findById(UUID id);

    List<E> findAll();

    void delete(E entity);

    void deleteAll();
}
