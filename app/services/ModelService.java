package services;

import dao.ModelDao;
import models.Model;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Manas Bajaj
 * <p>
 * Main service that provides CRUD operations for all SysML v2 models
 */

@Singleton
public class ModelService {
    @Inject
    private ModelDao dao;

    public List<Model> getAll() {
        return dao.findAll();
    }

    public Optional<Model> getById(UUID id) {
        return dao.findById(id);
    }

    public Optional<Model> create(Model model) {
        return model.getId() != null ? dao.update(model) : dao.persist(model);
    }
}
