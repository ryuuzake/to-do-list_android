package com.shiroecreative.todolist.module.edittask;

import com.shiroecreative.todolist.data.model.Task;

public class EditTaskPresenter implements EditTaskContract.Presenter {

    private final EditTaskContract.View view;
    private String id;

    public EditTaskPresenter(EditTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        loadData(id);
    }

    @Override
    public void saveData(String name, String time) {
        Task task = new Task("3", name, time);
        // Save new task
        // then go back to task list
        view.redirectToTaskList();
    }

    @Override
    public void loadData(String id) {
        // Load data task by id
        // then send data to fragment
        Task task = new Task(id, "New Task", "today");
        view.showData(task);
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void deleteData() {
        // Delete task
        // then go back to task list
        view.redirectToTaskList();
    }
}
