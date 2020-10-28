package com.shiroecreative.todolist.module.login;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void performLogin(String email, String pass) {
        view.redirectToHome();
    }

    @Override
    public void start() {

    }
}
