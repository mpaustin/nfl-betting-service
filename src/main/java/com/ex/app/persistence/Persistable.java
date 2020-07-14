package com.ex.app.persistence;

import java.util.List;

public interface Persistable<T, Integer, String> {

    List getAll();
    T getById(Integer id);
    void addNew(T obj);
    void update(T obj);
    void delete(T obj);

    boolean authenticate(java.lang.String username, java.lang.String password);
}
