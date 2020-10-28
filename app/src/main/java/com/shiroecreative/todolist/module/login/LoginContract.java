package com.shiroecreative.todolist.module.login;

import com.shiroecreative.todolist.base.BasePresenter;
import com.shiroecreative.todolist.base.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToHome();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String pass);
    }
}
