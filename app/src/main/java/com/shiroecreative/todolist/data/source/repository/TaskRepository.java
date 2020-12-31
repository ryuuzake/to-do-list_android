package com.shiroecreative.todolist.data.source.repository;

import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.utils.RequestResponseListener;

import java.util.List;

public interface TaskRepository {
    void createTask(Task task, RequestResponseListener<Task> listener);

    void updateTask(Task task, RequestResponseListener<Task> listener);

    void readAllTask(RequestResponseListener<List<Task>> listener);

    void readTaskById(String taskId, RequestResponseListener<Task> listener);

    void deleteTask(String taskId, RequestResponseListener<Task> listener);
}
