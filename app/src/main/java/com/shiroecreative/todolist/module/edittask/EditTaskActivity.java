package com.shiroecreative.todolist.module.edittask;

import com.shiroecreative.todolist.base.AppContainer;
import com.shiroecreative.todolist.base.BaseApplication;
import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;

import static com.shiroecreative.todolist.utils.Constants.TASK_ID;

public class EditTaskActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        final EditTaskFragment fragment = new EditTaskFragment();
        final AppContainer appContainer = ((BaseApplication) getApplication()).appContainer;

        EditTaskPresenter presenter = new EditTaskPresenter(
                fragment,
                appContainer.getTaskRepository(this)
        );
        fragment.setPresenter(presenter);

        String id = getIntent().getStringExtra(TASK_ID);
        presenter.setId(id);
        setCurrentFragment(fragment, true);
    }
}
