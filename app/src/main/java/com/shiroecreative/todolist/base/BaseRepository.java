package com.shiroecreative.todolist.base;

import java.util.List;

public interface BaseRepository<T> {
    void create(T t);

    T readById(String id);

    List<T> readAll();

    void update(T t);

    void delete(T t);
}
