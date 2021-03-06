package com.shiroecreative.todolist.base;

public interface BaseView<T> {
    void setPresenter(T presenter);

    void showError(String errorMessage);
}
