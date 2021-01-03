package com.shiroecreative.todolist.module.login;

import android.view.View;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;
import com.shiroecreative.todolist.data.source.remote.UserRemoteRepositoryImpl;
import com.shiroecreative.todolist.data.source.repository.UserGoogleRepository;
import com.shiroecreative.todolist.data.source.repository.UserRepositoryImpl;
import com.shiroecreative.todolist.data.source.session.UserSessionRepository;

public class LoginActivity extends BaseFragmentHolderActivity {

    LoginFragment loginFragment;

    @Override
    protected void initializeView() {
        super.initializeView();
        btnBack.setVisibility(View.GONE);
    }

    @Override
    protected void initializeFragment() {
        loginFragment = new LoginFragment();
        loginFragment.setPresenter(new LoginPresenter(
                loginFragment,
                new UserRepositoryImpl(
                        new UserSessionRepository(this),
                        new UserRemoteRepositoryImpl(),
                        new UserGoogleRepository(this)
                )
        ));
        setCurrentFragment(loginFragment, false);
    }
}
