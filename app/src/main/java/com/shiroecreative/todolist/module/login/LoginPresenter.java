package com.shiroecreative.todolist.module.login;

import android.content.Intent;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.LoginRequest;
import com.shiroecreative.todolist.data.request_response.VerifyRequest;
import com.shiroecreative.todolist.data.source.repository.UserRepository;
import com.shiroecreative.todolist.utils.ViewRequestResponseListener;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    private final UserRepository repository;

    public LoginPresenter(LoginContract.View view, UserRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void performLogin(String email, String pass) {
        repository.login(new LoginRequest(email, email, pass), new ViewRequestResponseListener<User>(view) {
            @Override
            public void onSuccess(User user) {
                view.redirectToHome();
            }
        });
    }

    @Override
    public Intent getSignInIntent() {
        return repository.getGoogleSignInIntent();
    }

    @Override
    public void handleSignInResult(Intent intent) {
        repository.handleSignInResult(intent, new ViewRequestResponseListener<User>(view) {
            @Override
            public void onSuccess(User user) {
                view.redirectToHome();
            }
        });
    }

    @Override
    public void start() {
        final User user = repository.getUser();
        if (user != null) {
            if (user.getToken() != null) {
                repository.checkToken(
                        new VerifyRequest(user.getToken()),
                        new ViewRequestResponseListener<Boolean>(view) {
                            @Override
                            public void onSuccess(Boolean isValid) {
                                view.redirectToHome();
                            }
                        }
                );
            } else {
                // Try Sign In with Google
                view.signInGoogle();
            }
        }
    }
}
