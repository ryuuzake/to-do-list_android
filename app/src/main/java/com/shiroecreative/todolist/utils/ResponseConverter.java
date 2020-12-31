package com.shiroecreative.todolist.utils;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.LoginResponse;

public class ResponseConverter {
    public static User fromLoginResponse(LoginResponse loginResponse) {
        final User user = new User();
        user.setEmail(loginResponse.getUser().getEmail());
        user.setToken(loginResponse.getToken());
        return user;
    }
}
