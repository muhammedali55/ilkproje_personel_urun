package com.muhammet.ilkproje.utility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class BaseService<T extends BaseEntity,ID> implements IService<T,ID>{

    private JpaRepository<T,ID> repository;

    public BaseService(JpaRepository<T,ID> repository){
        this.repository=repository;
    }

    @Override
    public T save(T entity) {
        entity.setCreateat(System.currentTimeMillis());
        entity.setUpdateat(System.currentTimeMillis());
        entity.setState(1);
        return repository.save(entity);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> entities) {
        entities.forEach(entity -> {
            entity.setCreateat(System.currentTimeMillis());
            entity.setUpdateat(System.currentTimeMillis());
        });
        return repository.saveAll(entities);
    }

    @Override
    public T update(T entity) {
        entity.setUpdateat(System.currentTimeMillis());
        return repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }


}
