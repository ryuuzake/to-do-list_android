package com.shiroecreative.todolist.module.register;

import com.shiroecreative.todolist.base.AppContainer;
import com.shiroecreative.todolist.base.BaseApplication;
import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;

public class RegisterActivity extends BaseFragmentHolderActivity {
    @Override
    protected void initializeFragment() {
        final RegisterFragment fragment = new RegisterFragment();
        final AppContainer appContainer = ((BaseApplication) getApplication()).appContainer;

        fragment.setPresenter(new RegisterPresenter(
                fragment,
                appContainer.getUserRepository(this)
        ));
        setCurrentFragment(fragment, true);
    }
}
