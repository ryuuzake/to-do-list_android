package com.shiroecreative.todolist.module.edittask;

import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;
import com.shiroecreative.todolist.data.source.local.TaskTableHandler;

import static com.shiroecreative.todolist.utils.Constants.TASK_ID;

public class EditTaskActivity extends BaseFragmentHolderActivity {

    EditTaskFragment editTaskFragment;

    @Override
    protected void initializeFragment() {
        initializeView();

        editTaskFragment = new EditTaskFragment();
        EditTaskPresenter presenter = new EditTaskPresenter(editTaskFragment, new TaskTableHandler(this));
        editTaskFragment.setPresenter(presenter);

        String id = getIntent().getStringExtra(TASK_ID);
        presenter.setId(id);
        setCurrentFragment(editTaskFragment, true);
    }
}
