package com.shiroecreative.todolist.module.addtask;

import com.shiroecreative.todolist.base.BasePresenter;
import com.shiroecreative.todolist.base.BaseView;

public interface AddTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();
    }

    interface Presenter extends BasePresenter {
        void saveData(String name, String time);
    }
}
