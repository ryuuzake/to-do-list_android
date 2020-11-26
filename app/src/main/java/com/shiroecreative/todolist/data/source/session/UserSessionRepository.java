package com.shiroecreative.todolist.data.source.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.shiroecreative.todolist.data.model.User;

public class UserSessionRepository extends SessionRepository<User> {
    private final static String SESSION_USER = "SessionUser";

    public UserSessionRepository(Context context) {
        super(context);
    }

    @Override
    public User getSessionData() {
        String sessionDataJson = sharedPrefs.getString(SESSION_USER, null);
        if (sessionDataJson != null) {
            return new Gson().fromJson(sessionDataJson, User.class);
        }
        return null;
    }

    @Override
    protected void setSessionData(User data) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(SESSION_USER, new Gson().toJson(data));
        editor.apply();
    }
}
