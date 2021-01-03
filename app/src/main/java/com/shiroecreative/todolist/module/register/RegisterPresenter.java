package com.shiroecreative.todolist.module.register;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.RegisterRequest;
import com.shiroecreative.todolist.data.source.repository.UserRepository;
import com.shiroecreative.todolist.utils.ViewRequestResponseListener;

public class RegisterPresenter implements RegisterContract.Presenter {
    private final RegisterContract.View view;
    private final UserRepository repository;

    public RegisterPresenter(RegisterContract.View view, UserRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {

    }

    @Override
    public void performRegister(String email, String username, String password, String confirmPassword) {
        repository.register(new RegisterRequest(email, username, password, confirmPassword),
                new ViewRequestResponseListener<User>(view) {
                    @Override
                    public void onSuccess(User user) {
                        view.redirectToLogin();
                    }
                }
        );
    }
}
