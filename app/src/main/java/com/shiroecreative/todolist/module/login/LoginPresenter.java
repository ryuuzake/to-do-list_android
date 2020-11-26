package com.shiroecreative.todolist.module.login;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.source.session.SessionRepository;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    private final SessionRepository<User> sessionRepository;

    public LoginPresenter(LoginContract.View view, SessionRepository<User> sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void performLogin(String email, String pass) {
        sessionRepository.initialize(new User(email, pass));
        view.redirectToHome();
    }

    @Override
    public void start() {
        if (sessionRepository.getSessionData() != null) {
            view.redirectToHome();
        }
    }
}
