package com.shiroecreative.todolist.module.login;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.LoginRequest;
import com.shiroecreative.todolist.data.source.repository.UserRepository;
import com.shiroecreative.todolist.utils.RequestResponseListener;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    private final UserRepository repository;

    public LoginPresenter(LoginContract.View view, UserRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void performLogin(String email, String pass) {
        repository.login(new LoginRequest(email, email, pass), new RequestResponseListener<User>() {
            @Override
            public void onSuccess(User user) {
                view.redirectToHome();
            }

            @Override
            public void onEmpty() {
                view.showError("Empty");
            }

            @Override
            public void onError(String errorMessage) {
                view.showError(errorMessage);
            }
        });
    }

    @Override
    public void start() {
        if (repository.getUser() != null) {
            view.redirectToHome();
        }
    }
}
