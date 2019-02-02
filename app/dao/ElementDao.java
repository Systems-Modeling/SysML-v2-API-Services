package dao;

import models.Element;
import models.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ElementDao extends Dao<Element> {
    Optional<Element> persist(Element Element);

    Optional<Element> update(Element entity);

    Optional<Element> findById(UUID id);

    List<Element> findAll();

    void delete(Element Element);

    void deleteAll();

    List<Element> findAllByModel(Model model);

    Optional<Element> findByModelAndId(Model model, UUID id);
}
