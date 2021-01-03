package com.shiroecreative.todolist.module.register;

import com.shiroecreative.todolist.base.BasePresenter;
import com.shiroecreative.todolist.base.BaseView;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void redirectToLogin();
    }

    interface Presenter extends BasePresenter {
        void performRegister(String toString, String toString1, String toString2, String toString3);
    }
}
