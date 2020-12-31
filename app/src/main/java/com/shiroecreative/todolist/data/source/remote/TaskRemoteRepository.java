package com.shiroecreative.todolist.data.source.remote;

import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.data.source.repository.TaskRepository;
import com.shiroecreative.todolist.utils.RequestResponseListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRemoteRepository extends RemoteRepository<TaskEndpoint> implements TaskRepository {
    public TaskRemoteRepository(String token) {
        super(TaskEndpoint.class, token);
    }

    @Override
    public void createTask(Task task, RequestResponseListener<Task> listener) {
        final Call<Task> call = endpoint.createTask(task);
        call.enqueue(getRemoteCallback(listener));
    }

    @Override
    public void updateTask(Task task, RequestResponseListener<Task> listener) {
        final Call<Task> call = endpoint.updateTask(task.getId(), task);
        call.enqueue(getRemoteCallback(listener));
    }

    @Override
    public void readTaskById(String taskId, RequestResponseListener<Task> listener) {
        final Call<Task> call = endpoint.getTask(taskId);
        call.enqueue(getRemoteCallback(listener));
    }

    @Override
    public void deleteTask(String taskId, RequestResponseListener<Task> listener) {
        final Call<ResponseBody> call = endpoint.deleteTask(taskId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess(null);
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                listener.onError(throwable.getMessage());
            }
        });
    }

    @Override
    public void readAllTask(RequestResponseListener<List<Task>> listener) {
        final Call<List<Task>> call = endpoint.getTasks();
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listener.onSuccess(response.body());
                    } else {
                        listener.onEmpty();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable throwable) {
                listener.onError(throwable.getMessage());
            }
        });
    }


    @NotNull
    private Callback<Task> getRemoteCallback(RequestResponseListener<Task> listener) {
        return new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listener.onSuccess(response.body());
                    } else {
                        listener.onEmpty();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable throwable) {
                listener.onError(throwable.getMessage());
            }
        };
    }
}
