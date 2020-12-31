package com.shiroecreative.todolist.data.source.repository;

import android.content.Intent;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.source.remote.UserRemoteRepository;
import com.shiroecreative.todolist.utils.RequestResponseListener;

public interface UserRepository extends UserRemoteRepository {

    User getUser();

    Intent getGoogleSignInIntent();

    void handleSignInResult(Intent intent, RequestResponseListener<User> listener);
}
