package services;

import dao.Dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BaseService<I, D extends Dao<I>> {

    protected final D dao;

    protected BaseService(D dao) {
        this.dao = dao;
    }

    public List<I> getAll() {
        return dao.findAll();
    }

    public Optional<I> getById(UUID id) {
        return dao.findById(id);
    }

    public Optional<I> persist(I i) {
        return dao.persist(i);
    }

    public Optional<I> update(I i) {
        return dao.update(i);
    }
}
