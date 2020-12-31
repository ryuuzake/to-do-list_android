package com.shiroecreative.todolist.data.source.repository;

import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.LoginRequest;
import com.shiroecreative.todolist.data.request_response.RegisterGoogleRequest;
import com.shiroecreative.todolist.data.request_response.RegisterRequest;
import com.shiroecreative.todolist.data.request_response.VerifyRequest;
import com.shiroecreative.todolist.data.source.remote.UserRemoteRepositoryImpl;
import com.shiroecreative.todolist.data.source.session.UserSessionRepository;
import com.shiroecreative.todolist.utils.RequestResponseListener;

import org.jetbrains.annotations.NotNull;

public class UserRepositoryImpl implements UserRepository {
    private final UserSessionRepository sessionRepository;
    private final UserRemoteRepositoryImpl remoteRepository;
    private final UserGoogleRepository googleRepository;

    public UserRepositoryImpl(UserSessionRepository sessionRepository,
                              UserRemoteRepositoryImpl remoteRepository,
                              UserGoogleRepository googleRepository) {
        this.sessionRepository = sessionRepository;
        this.remoteRepository = remoteRepository;
        this.googleRepository = googleRepository;
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
        final User user = googleRepository.getUser();
        if (user != null) {
            return user;
        }
        // If failed try from session
        return sessionRepository.getSessionData();
    }

    @Override
    public void checkToken(VerifyRequest verifyRequest, RequestResponseListener<Boolean> listener) {
        remoteRepository.checkToken(verifyRequest, listener);
    }

    @Override
    public void registerGoogleAccount(RegisterGoogleRequest registerGoogleRequest, RequestResponseListener<User> listener) {

    }

    @Override
    public Intent getGoogleSignInIntent() {
        return googleRepository.getSignInIntent();
    }

    @Override
    public void handleSignInResult(Intent intent, RequestResponseListener<User> listener) {
        googleRepository.handleSignInResult(intent, new RequestResponseListener<GoogleSignInAccount>() {
            @Override
            public void onSuccess(GoogleSignInAccount googleSignInAccount) {
                remoteRepository.registerGoogleAccount(new RegisterGoogleRequest(googleSignInAccount.getIdToken()), getRemoteListener(listener));
            }

            @Override
            public void onEmpty() {
                listener.onEmpty();
            }

            @Override
            public void onError(String errorMessage) {
                listener.onError(errorMessage);
            }
        });
    }
}
