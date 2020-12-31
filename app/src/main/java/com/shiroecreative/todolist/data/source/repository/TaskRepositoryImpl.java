package com.shiroecreative.todolist.data.source.repository;

import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.data.source.local.TaskTableHandler;
import com.shiroecreative.todolist.data.source.remote.TaskRemoteRepository;
import com.shiroecreative.todolist.utils.RequestResponseListener;

import java.util.List;

public class TaskRepositoryImpl implements TaskRepository {

    private final TaskTableHandler handler;
    private final TaskRepository remoteRepository;

    public TaskRepositoryImpl(TaskTableHandler handler, TaskRemoteRepository remoteRepository) {
        this.handler = handler;
        this.remoteRepository = remoteRepository;
    }

    @Override
    public void createTask(Task task, RequestResponseListener<Task> listener) {
        remoteRepository.createTask(task, listener);
    }

    @Override
    public void updateTask(Task task, RequestResponseListener<Task> listener) {
        remoteRepository.updateTask(task, listener);
    }

    @Override
    public void readAllTask(RequestResponseListener<List<Task>> listener) {
        remoteRepository.readAllTask(new RequestResponseListener<List<Task>>() {
            @Override
            public void onSuccess(List<Task> tasks) {
                // Yeah, I know it's weird. But bear with me
                for (Task task : tasks) handler.create(task);
                listener.onSuccess(tasks);
            }

            @Override
            public void onEmpty() {
                listener.onEmpty();
            }

            @Override
            public void onError(String errorMessage) {
                // Get from local if can't connect to network
                try {
                    final List<Task> tasks = handler.readAll();
                    if (tasks != null && tasks.size() > 0) {
                        listener.onSuccess(tasks);
                    }
                } catch (Exception e) {
                    listener.onError(errorMessage);
                }
            }
        });
    }

    @Override
    public void readTaskById(String taskId, RequestResponseListener<Task> listener) {
        remoteRepository.readTaskById(taskId, new RequestResponseListener<Task>() {
            @Override
            public void onSuccess(Task task) {
                handler.create(task);
                listener.onSuccess(task);
            }

            @Override
            public void onEmpty() {
                listener.onEmpty();
            }

            @Override
            public void onError(String errorMessage) {
                // Get from local if can't connect to network
                try {
                    final Task task = handler.readById(taskId);

                    if (task != null) {
                        listener.onSuccess(task);
                    }
                } catch (Exception e) {
                    listener.onError(errorMessage);
                }
            }
        });
    }

    @Override
    public void deleteTask(String taskId, RequestResponseListener<Task> listener) {
        remoteRepository.deleteTask(taskId, listener);
    }
}
