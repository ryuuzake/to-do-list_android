package com.shiroecreative.todolist.data.source.session;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class SessionRepository<T> {
    protected final static String SESSION_KEY = "SessionKey";
    protected final SharedPreferences sharedPrefs;

    public SessionRepository(Context context) {
        sharedPrefs = context.getSharedPreferences(SESSION_KEY, Context.MODE_PRIVATE);
    }

    public T initialize(T data) {
        // save to shared preference
        setSessionData(data);

        // load from shared preference
        return getSessionData();
    }

    public void destroy() {
        sharedPrefs.edit().clear().apply();
    }

    public void update(T data) {
        destroy();
        setSessionData(data);
    }

    abstract public T getSessionData();

    abstract protected void setSessionData(T data);

}
