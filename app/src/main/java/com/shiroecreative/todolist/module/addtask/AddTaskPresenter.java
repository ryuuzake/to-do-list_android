package com.shiroecreative.todolist.module.addtask;

import com.shiroecreative.todolist.data.model.Task;

public class AddTaskPresenter implements AddTaskContract.Presenter {

    private final AddTaskContract.View view;

    public AddTaskPresenter(AddTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveData(String name, String time) {
        Task task = new Task("3", name, time);
        // Save new task
        view.redirectToTaskList();
    }
}
