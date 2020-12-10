package com.shiroecreative.todolist.module.edittask;

import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.data.source.local.TableHandler;

public class EditTaskPresenter implements EditTaskContract.Presenter {

    private final EditTaskContract.View view;
    private final TableHandler handler;
    private String id;

    public EditTaskPresenter(EditTaskContract.View view, TableHandler handler) {
        this.view = view;
        this.handler = handler;
    }

    @Override
    public void start() {
        loadData(id);
    }

    @Override
    public void saveData(String name, String time) {
        Task task = new Task(id, name, time);
        // Save new task
        handler.update(task);
        // then go back to task list
        view.redirectToTaskList();
    }

    @Override
    public void loadData(String id) {
        // Load data task by id
        // then send data to fragment
        Task task = (Task) handler.readById(id);
        view.showData(task);
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void deleteData() {
        // Delete task
        handler.delete(new Task(id, null, null));
        // then go back to task list
        view.redirectToTaskList();
    }
}
