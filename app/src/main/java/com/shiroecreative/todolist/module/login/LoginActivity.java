package com.shiroecreative.todolist.module.login;

import android.view.View;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;

public class LoginActivity extends BaseFragmentHolderActivity {

    LoginFragment loginFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        btnBack.setVisibility(View.GONE);

        loginFragment = new LoginFragment();
        LoginPresenter presenter = new LoginPresenter(loginFragment);
        loginFragment.setPresenter(presenter);
        setCurrentFragment(loginFragment, false);
    }
}
