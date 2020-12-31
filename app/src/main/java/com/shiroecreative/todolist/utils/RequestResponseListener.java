package com.shiroecreative.todolist.utils;

import com.shiroecreative.todolist.base.BaseModel;

public interface RequestResponseListener<U extends BaseModel> {
    void onSuccess(U u);

    void onEmpty();

    void onError(String errorMessage);
}
