package com.shiroecreative.todolist.data.source.local;

import java.util.List;

public interface TableHandler<T> {
    void create(T t);

    T readById(String id);

    List<T> readAll();

    void update(T t);

    void delete(T t);
}
