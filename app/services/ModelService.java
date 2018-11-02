package services;

import dao.ModelDAO;
import models.Model;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Singleton
public class ModelService {
    @Inject
    private ModelDAO dao;

    public Model create(Model model) {
        return dao.create(model);
    }

    public void delete(UUID modelId) {
        dao.delete(modelId);
    }

    public void deleteAll() {
        dao.deleteAll();
    }

    public Model getById(UUID modelId) {
        return dao.getById(modelId);
    }

    public List<Model> getAll() {
        return dao.getAll();
    }

    public Model update(Model model) {
        return dao.update(model);
    }

    public List<Model> updateAll(Collection<Model> deserialized) {
        return dao.updateAll(deserialized);
    }
}
