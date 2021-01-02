package com.shiroecreative.todolist.module.home;

import android.view.View;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;
import com.shiroecreative.todolist.data.source.local.TaskTableHandler;
import com.shiroecreative.todolist.data.source.remote.TaskRemoteRepository;
import com.shiroecreative.todolist.data.source.remote.UserRemoteRepositoryImpl;
import com.shiroecreative.todolist.data.source.repository.TaskRepositoryImpl;
import com.shiroecreative.todolist.data.source.repository.UserGoogleRepository;
import com.shiroecreative.todolist.data.source.repository.UserRepositoryImpl;
import com.shiroecreative.todolist.data.source.session.UserSessionRepository;

public class HomeActivity extends BaseFragmentHolderActivity {

    HomeFragment homeFragment;

    @Override
    protected void initializeView() {
        super.initializeView();

        btnBack.setVisibility(View.GONE);
        btnLogout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initializeFragment() {
        homeFragment = new HomeFragment();
        HomePresenter presenter = new HomePresenter(homeFragment, new TaskRepositoryImpl(
                new TaskTableHandler(this),
                new TaskRemoteRepository(new UserSessionRepository(this).getSessionData().getToken())
        ), new UserRepositoryImpl(new UserSessionRepository(this), new UserRemoteRepositoryImpl(), new UserGoogleRepository(this)));
        homeFragment.setPresenter(presenter);
        setCurrentFragment(homeFragment, false);
    }

}
