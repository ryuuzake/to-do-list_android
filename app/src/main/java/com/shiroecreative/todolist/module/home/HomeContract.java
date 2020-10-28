package com.shiroecreative.todolist.module.home;

import com.shiroecreative.todolist.base.BasePresenter;
import com.shiroecreative.todolist.base.BaseView;
import com.shiroecreative.todolist.data.model.Task;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void sendToAddTask();

        void sendToEditTask(String id);
    }

    interface Presenter extends BasePresenter {
        void addTask();

        void editTask(String id);

        List<Task> getTasks();
    }
}
