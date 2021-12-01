package com.pas.rest_pas.dao;

import com.pas.rest_pas.entities.Entity;

import java.util.*;

public abstract class AbstractRepository<T extends Entity> {

    private final Set<T> repository = new HashSet<>();


    // C
    public boolean add(T t) {
        return repository.add(t);
    }

    // R
    public T getById(UUID id) {
        return repository
                .stream()
                .filter(user -> id.equals(user.getId()))
                .findAny()
                .orElse(null);
    }

    public List<T> getAll() {
        return new ArrayList<>(repository);
    }


    // U
    public boolean update(T t, UUID id) {
        T obj = getById(id);
        if (!obj.equals(null)) {
            repository.remove(obj);
            t.setId(id);
            repository.add(t);
            return true;
        }
        else return false;

    }


    // D
    public boolean delete(T t) {
        return repository.remove(t);
    }





}
