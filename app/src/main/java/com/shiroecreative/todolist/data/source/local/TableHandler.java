package com.shiroecreative.todolist.data.source.local;

import android.content.Context;

import java.util.List;

public abstract class TableHandler<T> {
    protected DatabaseHelper dbHelper;

    public TableHandler(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    abstract void create(T t);

    abstract T readById(String id);

    abstract List<T> readAll();

    abstract void update(T t);

    abstract void delete(T t);
}
