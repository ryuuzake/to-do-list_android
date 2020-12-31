package com.shiroecreative.todolist.module.home;

import android.view.View;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;
import com.shiroecreative.todolist.data.source.local.TaskTableHandler;
import com.shiroecreative.todolist.data.source.remote.TaskRemoteRepository;
import com.shiroecreative.todolist.data.source.repository.TaskRepositoryImpl;
import com.shiroecreative.todolist.data.source.session.UserSessionRepository;

public class HomeActivity extends BaseFragmentHolderActivity {

    HomeFragment homeFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        btnBack.setVisibility(View.GONE);

        homeFragment = new HomeFragment();
        HomePresenter presenter = new HomePresenter(homeFragment, new TaskRepositoryImpl(
                new TaskTableHandler(this),
                new TaskRemoteRepository(new UserSessionRepository(this).getSessionData().getToken())
        ));
        homeFragment.setPresenter(presenter);
        setCurrentFragment(homeFragment, false);
    }

}
