package com.shiroecreative.todolist.utils;

public interface RequestResponseListener<U> {
    void onSuccess(U u);

    void onEmpty();

    void onError(String errorMessage);
}
