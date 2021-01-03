package com.shiroecreative.todolist.module.home;

import android.view.View;

import com.shiroecreative.todolist.base.AppContainer;
import com.shiroecreative.todolist.base.BaseApplication;
import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;

public class HomeActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeView() {
        super.initializeView();

        btnBack.setVisibility(View.GONE);
        btnLogout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initializeFragment() {
        final HomeFragment fragment = new HomeFragment();
        final AppContainer appContainer = ((BaseApplication) getApplication()).appContainer;

        HomePresenter presenter = new HomePresenter(
                fragment,
                appContainer.getTaskRepository(this),
                appContainer.getUserRepository(this)
        );

        fragment.setPresenter(presenter);
        setCurrentFragment(fragment, false);
    }

}
