package com.shiroecreative.todolist.data.source.repository;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.LoginRequest;
import com.shiroecreative.todolist.data.request_response.RegisterRequest;
import com.shiroecreative.todolist.utils.RequestResponseListener;

public interface UserRepository {
    void register(RegisterRequest registerRequest, RequestResponseListener<User> listener);

    void login(LoginRequest loginRequest, RequestResponseListener<User> listener);

    User getUser();
}
