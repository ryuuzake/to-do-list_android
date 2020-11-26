package com.shiroecreative.todolist.module.login;

import android.view.View;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;
import com.shiroecreative.todolist.data.source.session.UserSessionRepository;

public class LoginActivity extends BaseFragmentHolderActivity {

    LoginFragment loginFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        btnBack.setVisibility(View.GONE);

        loginFragment = new LoginFragment();
        LoginPresenter presenter = new LoginPresenter(loginFragment, new UserSessionRepository(this));
        loginFragment.setPresenter(presenter);
        setCurrentFragment(loginFragment, false);
    }
}
