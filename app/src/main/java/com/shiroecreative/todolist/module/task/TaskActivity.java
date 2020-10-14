package com.shiroecreative.todolist.module.task;

import android.content.Intent;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;

import static com.shiroecreative.todolist.module.Constants.TASK_TYPE;

public class TaskActivity extends BaseFragmentHolderActivity {

    TaskFragment taskFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        taskFragment = new TaskFragment();
        TaskPresenter presenter = new TaskPresenter(taskFragment);
        taskFragment.setPresenter(presenter);

        Intent intent = getIntent();
        presenter.setTaskType(intent.getStringExtra(TASK_TYPE));
        setCurrentFragment(taskFragment, true);
    }
}
