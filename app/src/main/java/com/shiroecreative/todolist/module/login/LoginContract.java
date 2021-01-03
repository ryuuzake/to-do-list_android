package com.shiroecreative.todolist.module.login;

import android.content.Intent;

import com.shiroecreative.todolist.base.BasePresenter;
import com.shiroecreative.todolist.base.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void signInGoogle();

        void redirectToHome();

        void redirectToRegister();
    }

    interface Presenter extends BasePresenter {
        void performLogin(String email, String pass);

        Intent getSignInIntent();

        void handleSignInResult(Intent intent);
    }
}
