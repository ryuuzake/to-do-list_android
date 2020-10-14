package com.shiroecreative.todolist.module.home;

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
    public void editTask() {
        view.sendToEditTask();
    }
}
