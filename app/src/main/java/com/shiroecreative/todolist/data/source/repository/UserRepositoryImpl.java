package com.shiroecreative.todolist.data.source.repository;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.LoginRequest;
import com.shiroecreative.todolist.data.request_response.RegisterRequest;
import com.shiroecreative.todolist.data.request_response.VerifyRequest;
import com.shiroecreative.todolist.data.source.remote.UserRemoteRepository;
import com.shiroecreative.todolist.data.source.session.UserSessionRepository;
import com.shiroecreative.todolist.utils.RequestResponseListener;

import org.jetbrains.annotations.NotNull;

public class UserRepositoryImpl implements UserRepository {
    private final UserSessionRepository sessionRepository;
    private final UserRemoteRepository remoteRepository;

    public UserRepositoryImpl(UserSessionRepository sessionRepository,
                              UserRemoteRepository remoteRepository) {
        this.sessionRepository = sessionRepository;
        this.remoteRepository = remoteRepository;
    }

    @Override
    public void register(RegisterRequest registerRequest, RequestResponseListener<User> listener) {
        remoteRepository.register(registerRequest, getRemoteListener(listener));
    }

    @Override
    public void login(LoginRequest loginRequest, RequestResponseListener<User> listener) {
        remoteRepository.login(loginRequest, getRemoteListener(listener));
    }

    @NotNull
    private RequestResponseListener<User> getRemoteListener(RequestResponseListener<User> listener) {
        return new RequestResponseListener<User>() {
            @Override
            public void onSuccess(User user) {
                sessionRepository.initialize(user);
                listener.onSuccess(user);
            }

            @Override
            public void onEmpty() {
                listener.onEmpty();
            }

            @Override
            public void onError(String errorMessage) {
                listener.onError(errorMessage);
            }
        };
    }

    @Override
    public User getUser() {
        return sessionRepository.getSessionData();
    }

    @Override
    public void checkToken(VerifyRequest verifyRequest, RequestResponseListener<Boolean> listener) {
        remoteRepository.checkToken(verifyRequest, listener);
    }
}
