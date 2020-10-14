package com.shiroecreative.todolist.module.home;

import com.shiroecreative.todolist.base.BasePresenter;
import com.shiroecreative.todolist.base.BaseView;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void sendToAddTask();

        void sendToEditTask();
    }

    interface Presenter extends BasePresenter {
        void addTask();

        void editTask();
    }
}
