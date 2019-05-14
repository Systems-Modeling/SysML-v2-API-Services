package dao;

import models.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModelDao extends Dao<Model> {
    Optional<Model> persist(Model Model);

    Optional<Model> update(Model entity);

    Optional<Model> findById(UUID id);

    List<Model> findAll();

    void delete(Model Model);

    void deleteAll();
}
