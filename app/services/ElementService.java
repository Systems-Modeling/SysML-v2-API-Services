package services;

import dao.ElementDAO;
import models.Element;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Singleton
public class ElementService {
    @Inject
    private ElementDAO dao;

    public Element create(Element element) {
        return dao.create(element);
    }

    public void delete(UUID modelId, UUID elementId) {
        dao.delete(modelId, elementId);
    }

    public void deleteAll(UUID modelId) {
        dao.deleteAll(modelId);
    }

    public Element getById(UUID modelId, UUID elementId) {
        return dao.getById(modelId, elementId);
    }

    public List<Element> getAll(UUID modelId) {
        return dao.getAll(modelId);
    }

    public Element update(Element element) {
        return dao.update(element);
    }

    public List<Element> updateAll(UUID modelId, Collection<Element> deserialized) {
        return dao.updateAll(modelId, deserialized);
    }
}
