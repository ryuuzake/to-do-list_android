package com.shiroecreative.todolist.module.login;

import android.view.View;

import com.shiroecreative.todolist.base.AppContainer;
import com.shiroecreative.todolist.base.BaseApplication;
import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;

public class LoginActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeView() {
        super.initializeView();
        btnBack.setVisibility(View.GONE);
    }

    @Override
    protected void initializeFragment() {
        final LoginFragment fragment = new LoginFragment();
        final AppContainer appContainer = ((BaseApplication) getApplication()).appContainer;

        fragment.setPresenter(new LoginPresenter(
                fragment,
                appContainer.getUserRepository(this)
        ));
        setCurrentFragment(fragment, false);
    }
}
