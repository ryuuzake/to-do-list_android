package com.shiroecreative.todolist.module.addtask;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;
import com.shiroecreative.todolist.data.source.local.TaskTableHandler;

public class AddTaskActivity extends BaseFragmentHolderActivity {

    AddTaskFragment addTaskFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        addTaskFragment = new AddTaskFragment();
        AddTaskPresenter presenter = new AddTaskPresenter(addTaskFragment, new TaskTableHandler(this));
        addTaskFragment.setPresenter(presenter);

        setCurrentFragment(addTaskFragment, true);
    }
}
