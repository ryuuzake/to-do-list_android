package com.shiroecreative.todolist.module.addtask;

import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.data.source.local.TableHandler;

public class AddTaskPresenter implements AddTaskContract.Presenter {

    private final AddTaskContract.View view;
    private final TableHandler handler;

    public AddTaskPresenter(AddTaskContract.View view, TableHandler handler) {
        this.view = view;
        this.handler = handler;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveData(String name, String time) {
        Task task = new Task("3", name, time);
        // Save new task
        handler.create(task);
        view.redirectToTaskList();
    }
}
