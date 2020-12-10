package com.shiroecreative.todolist.module.home;

import android.view.View;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;
import com.shiroecreative.todolist.data.source.local.TaskTableHandler;

public class HomeActivity extends BaseFragmentHolderActivity {

    HomeFragment homeFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        btnBack.setVisibility(View.GONE);

        homeFragment = new HomeFragment();
        HomePresenter presenter = new HomePresenter(homeFragment, new TaskTableHandler(this));
        homeFragment.setPresenter(presenter);
        setCurrentFragment(homeFragment, false);
    }

}
