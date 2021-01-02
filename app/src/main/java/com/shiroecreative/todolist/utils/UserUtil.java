package com.shiroecreative.todolist.utils;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.LoginResponse;

public class UserUtil {
    public static User fromLoginResponse(LoginResponse loginResponse) {
        final User user = new User();
        user.setUsername(loginResponse.getUser().getUsername());
        user.setEmail(loginResponse.getUser().getEmail());
        user.setToken(loginResponse.getToken());
        return user;
    }

    public static User fromGoogleResponse(GoogleSignInAccount account) {
        final User user = new User();
        user.setUsername(account.getDisplayName());
        user.setEmail(account.getEmail());
        return user;
    }
}
