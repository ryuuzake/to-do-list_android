package com.shiroecreative.todolist.module.addtask;

import com.shiroecreative.todolist.base.AppContainer;
import com.shiroecreative.todolist.base.BaseApplication;
import com.shiroecreative.todolist.base.BaseFragmentHolderActivity;

public class AddTaskActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        final AddTaskFragment fragment = new AddTaskFragment();
        final AppContainer appContainer = ((BaseApplication) getApplication()).appContainer;

        AddTaskPresenter presenter = new AddTaskPresenter(
                fragment,
                appContainer.getTaskRepository(this)
        );
        fragment.setPresenter(presenter);

        setCurrentFragment(fragment, true);
    }
}
