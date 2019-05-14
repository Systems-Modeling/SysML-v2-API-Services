package services;

import dao.ElementDao;
import dao.ModelDao;
import models.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class ElementService {
    @Inject
    private ElementDao elementDao;

    @Inject
    private ModelDao modelDao;

    public List<Element> getAll() {
        return elementDao.findAll();
    }

    public Optional<Element> getById(UUID id) {
        return elementDao.findById(id);
    }

    public List<Element> getByModelId(UUID modelId) {
        return modelDao.findById(modelId).map(m -> elementDao.findAllByModel(m)).orElse(Collections.emptyList());
    }

    public Optional<Element> getByModelIdAndId(UUID modelId, UUID elementId) {
        return modelDao.findById(modelId).flatMap(m -> elementDao.findByModelAndId(m, elementId));
    }

    public Optional<Element> create(Element element) {
        return element.getId() != null ? elementDao.update(element) : elementDao.persist(element);
    }
}
