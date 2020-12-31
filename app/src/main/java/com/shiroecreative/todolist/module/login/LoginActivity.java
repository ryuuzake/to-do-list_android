package com.shiroecreative.todolist.module.login;

import android.view.View;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;
import com.shiroecreative.todolist.data.source.remote.UserRemoteRepository;
import com.shiroecreative.todolist.data.source.repository.UserRepositoryImpl;
import com.shiroecreative.todolist.data.source.session.UserSessionRepository;

public class LoginActivity extends BaseFragmentHolderActivity {

    LoginFragment loginFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        btnBack.setVisibility(View.GONE);

        loginFragment = new LoginFragment();
        loginFragment.setPresenter(new LoginPresenter(
                loginFragment,
                new UserRepositoryImpl(
                        new UserSessionRepository(this),
                        new UserRemoteRepository()
                )
        ));
        setCurrentFragment(loginFragment, false);
    }
}
