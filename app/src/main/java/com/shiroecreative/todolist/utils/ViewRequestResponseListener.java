package com.shiroecreative.todolist.utils;

import com.shiroecreative.todolist.base.BaseView;

public abstract class ViewRequestResponseListener<U> implements RequestResponseListener<U> {

    private BaseView view;

    public ViewRequestResponseListener(BaseView view) {
        this.view = view;
    }

    @Override
    public void onEmpty() {
        view.showError("Empty Response From Server");
    }

    @Override
    public void onError(String errorMessage) {
        view.showError(errorMessage);
    }
}
