package com.pie.planingjakartaee.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(int id);

    Optional<T> getPassword(String email);
    List<T> getAll();
    void create(T t);
    void update(T t);
    void delete(T t);
}
