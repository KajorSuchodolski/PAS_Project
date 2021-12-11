package com.pas.rest_pas.repositories;

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
                .filter(e -> id.equals(e.getId()))
                .findAny()
                .orElse(null);
    }

    public List<T> getAll() {
        return new ArrayList<>(repository);
    }

    // D
    public boolean delete(T t) {
        return repository.remove(t);
    }


}
