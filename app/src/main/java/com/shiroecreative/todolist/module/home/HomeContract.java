package com.shiroecreative.todolist.module.home;

import com.shiroecreative.todolist.base.BasePresenter;
import com.shiroecreative.todolist.base.BaseView;
import com.shiroecreative.todolist.data.model.Task;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showTasks(List<Task> tasks);

        void sendToAddTask();

        void sendToEditTask(String id);

        void sendToLogin();
    }

    interface Presenter extends BasePresenter {
        void addTask();

        void editTask(String id);

        void getTasks();

        void checkedTask(Task task);

        void logout();
    }
}
