package com.shiroecreative.todolist.data.source.remote;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.LoginRequest;
import com.shiroecreative.todolist.data.request_response.RegisterGoogleRequest;
import com.shiroecreative.todolist.data.request_response.RegisterRequest;
import com.shiroecreative.todolist.data.request_response.VerifyRequest;
import com.shiroecreative.todolist.utils.RequestResponseListener;

public interface UserRemoteRepository {
    void register(RegisterRequest registerRequest, RequestResponseListener<User> listener);

    void login(LoginRequest loginRequest, RequestResponseListener<User> listener);

    void checkToken(VerifyRequest verifyRequest, RequestResponseListener<Boolean> listener);

    void registerGoogleAccount(RegisterGoogleRequest registerGoogleRequest, RequestResponseListener<User> listener);
}
