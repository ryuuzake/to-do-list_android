package com.shiroecreative.todolist.module.edittask;

import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.data.source.repository.TaskRepository;
import com.shiroecreative.todolist.utils.ViewRequestResponseListener;

public class EditTaskPresenter implements EditTaskContract.Presenter {

    private final EditTaskContract.View view;
    private final TaskRepository repository;
    private String id;

    public EditTaskPresenter(EditTaskContract.View view, TaskRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void start() {
        loadData(id);
    }

    @Override
    public void saveData(Task task) {
        task.setId(id);
        // Save new task
        repository.updateTask(task, new ViewRequestResponseListener<Task>(view) {
            @Override
            public void onSuccess(Task task) {
                // then go back to task list
                view.redirectToTaskList();
            }
        });
    }

    @Override
    public void loadData(String id) {
        // Load data task by id
        // then send data to fragment
        repository.readTaskById(id, new ViewRequestResponseListener<Task>(view) {
            @Override
            public void onSuccess(Task task) {
                view.showData(task);
            }
        });
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void deleteData() {
        // Delete task
        repository.deleteTask(id, new ViewRequestResponseListener<Task>(view) {
            @Override
            public void onSuccess(Task task) {
                // then go back to task list
                view.redirectToTaskList();
            }
        });
    }
}
