package com.pas.rest_pas.dao;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(long id);
    List<T> getAll();
    void add(T t);
    void remove(T t);
}
