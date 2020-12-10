package com.shiroecreative.todolist.module.home;

import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.data.source.local.TableHandler;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View view;
    private final TableHandler handler;

    public HomePresenter(HomeContract.View view, TableHandler handler) {
        this.view = view;
        this.handler = handler;
    }

    @Override
    public void start() {

    }

    @Override
    public void addTask() {
        view.sendToAddTask();
    }

    @Override
    public void editTask(String id) {
        view.sendToEditTask(id);
    }

    @Override
    public void getTasks() {
        // This should be getting data from DB
        List<Task> tasks = handler.readAll();
        // Show data in view
        view.showTasks(tasks);
    }
}
