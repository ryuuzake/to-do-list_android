package com.shiroecreative.todolist.base;

import android.content.Context;

import com.shiroecreative.todolist.data.source.local.TaskTableHandler;
import com.shiroecreative.todolist.data.source.remote.TaskRemoteRepository;
import com.shiroecreative.todolist.data.source.remote.UserRemoteRepositoryImpl;
import com.shiroecreative.todolist.data.source.repository.TaskRepository;
import com.shiroecreative.todolist.data.source.repository.TaskRepositoryImpl;
import com.shiroecreative.todolist.data.source.repository.UserGoogleRepository;
import com.shiroecreative.todolist.data.source.repository.UserRepository;
import com.shiroecreative.todolist.data.source.repository.UserRepositoryImpl;
import com.shiroecreative.todolist.data.source.session.UserSessionRepository;

public class AppContainer {

    public UserSessionRepository getUserSessionRepository(Context context) {
        return new UserSessionRepository(context);
    }

    public TaskRepository getTaskRepository(Context context) {
        return new TaskRepositoryImpl(
                new TaskTableHandler(context),
                new TaskRemoteRepository(getUserSessionRepository(context).getSessionData().getToken())
        );
    }

    public UserRepository getUserRepository(Context context) {
        return new UserRepositoryImpl(
                getUserSessionRepository(context),
                new UserRemoteRepositoryImpl(),
                new UserGoogleRepository(context)
        );
    }
}
