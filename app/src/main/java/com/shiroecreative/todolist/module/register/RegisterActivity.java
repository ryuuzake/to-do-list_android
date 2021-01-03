package com.shiroecreative.todolist.module.register;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;
import com.shiroecreative.todolist.data.source.remote.UserRemoteRepositoryImpl;
import com.shiroecreative.todolist.data.source.repository.UserGoogleRepository;
import com.shiroecreative.todolist.data.source.repository.UserRepositoryImpl;
import com.shiroecreative.todolist.data.source.session.UserSessionRepository;

public class RegisterActivity extends BaseFragmentHolderActivity {
    @Override
    protected void initializeFragment() {
        final RegisterFragment fragment = new RegisterFragment();
        fragment.setPresenter(new RegisterPresenter(
                fragment,
                new UserRepositoryImpl(
                        new UserSessionRepository(this),
                        new UserRemoteRepositoryImpl(),
                        new UserGoogleRepository(this)
                )
        ));
        setCurrentFragment(fragment, true);
    }
}
