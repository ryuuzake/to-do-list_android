package com.shiroecreative.todolist.module.task;

public class TaskPresenter implements TaskContract.Presenter {

    private final TaskContract.View view;
    private String taskType;

    public TaskPresenter(TaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        if (taskType != null && taskType.equals("ADD")) {
            view.setupAddTask();
        } else {
            view.setupEditTask();
        }
    }

    @Override
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
