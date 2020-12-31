package com.shiroecreative.todolist.data.source.remote;

import com.shiroecreative.todolist.data.model.Task;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TaskEndpoint {
    String TASK_ENDPOINT = "tasks/";

    @POST(TASK_ENDPOINT)
    Call<Task> createTask(@Body Task task);

    @GET(TASK_ENDPOINT)
    Call<List<Task>> getTasks();

    @GET(TASK_ENDPOINT + "{id}/")
    Call<Task> getTask(@Path("id") String taskId);

    @PUT(TASK_ENDPOINT + "{id}/")
    Call<Task> updateTask(@Path("id") String taskId, @Body Task task);

    @DELETE(TASK_ENDPOINT + "{id}/")
    Call<ResponseBody> deleteTask(@Path("id") String taskId);
}
