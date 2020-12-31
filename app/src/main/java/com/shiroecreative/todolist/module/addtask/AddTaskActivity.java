package com.shiroecreative.todolist.module.addtask;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;
import com.shiroecreative.todolist.data.source.local.TaskTableHandler;
import com.shiroecreative.todolist.data.source.remote.TaskRemoteRepository;
import com.shiroecreative.todolist.data.source.repository.TaskRepositoryImpl;
import com.shiroecreative.todolist.data.source.session.UserSessionRepository;

public class AddTaskActivity extends BaseFragmentHolderActivity {

    AddTaskFragment addTaskFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        addTaskFragment = new AddTaskFragment();
        AddTaskPresenter presenter = new AddTaskPresenter(addTaskFragment,
                new TaskRepositoryImpl(
                        new TaskTableHandler(this),
                        new TaskRemoteRepository(new UserSessionRepository(this).getSessionData().getToken())
                )
        );
        addTaskFragment.setPresenter(presenter);

        setCurrentFragment(addTaskFragment, true);
    }
}
