package com.shiroecreative.todolist.module.home;

import com.shiroecreative.todolist.data.model.Task;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private final HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
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
    public List<Task> getTasks() {
        // This should be getting data from DB
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("1", "Work on Backend", "Today"));
        tasks.add(new Task("2", "Testing API", "Tomorrow"));

        return tasks;
    }
}
