package com.shiroecreative.todolist.module.home;

import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.data.source.repository.TaskRepository;
import com.shiroecreative.todolist.utils.ViewRequestResponseListener;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View view;
    private final TaskRepository repository;

    public HomePresenter(HomeContract.View view, TaskRepository repository) {
        this.view = view;
        this.repository = repository;
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
        repository.readAllTask(new ViewRequestResponseListener<List<Task>>(view) {
            @Override
            public void onSuccess(List<Task> tasks) {
                // Show data in view
                view.showTasks(tasks);
            }
        });

    }
}
