package com.shiroecreative.todolist.module.addtask;

import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.data.source.repository.TaskRepository;
import com.shiroecreative.todolist.utils.ViewRequestResponseListener;

public class AddTaskPresenter implements AddTaskContract.Presenter {

    private final AddTaskContract.View view;
    private final TaskRepository repository;

    public AddTaskPresenter(AddTaskContract.View view, TaskRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveData(Task task) {
        // Save new task
        repository.createTask(task, new ViewRequestResponseListener<Task>(view) {
            @Override
            public void onSuccess(Task task) {
                view.redirectToTaskList();
            }
        });
    }
}
