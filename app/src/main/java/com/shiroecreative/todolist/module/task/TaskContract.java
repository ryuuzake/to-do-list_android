package com.shiroecreative.todolist.module.task;

import com.shiroecreative.todolist.base.BasePresenter;
import com.shiroecreative.todolist.base.BaseView;

public interface TaskContract {
    interface View extends BaseView<Presenter> {
        void setupAddTask();

        void setupEditTask();
    }

    interface Presenter extends BasePresenter {
        void setTaskType(String taskType);
    }
}
