package com.shiroecreative.todolist.data.source.remote;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.LoginRequest;
import com.shiroecreative.todolist.data.request_response.LoginResponse;
import com.shiroecreative.todolist.data.request_response.RegisterRequest;
import com.shiroecreative.todolist.data.source.repository.UserRepository;
import com.shiroecreative.todolist.utils.RequestResponseListener;
import com.shiroecreative.todolist.utils.ResponseConverter;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRemoteRepository extends RemoteRepository<UserEndpoint> implements UserRepository {

    public UserRemoteRepository(Class<UserEndpoint> endpoint) {
        super(endpoint);
    }

    @Override
    public void register(RegisterRequest registerRequest, RequestResponseListener<User> listener) {
        final Call<LoginResponse> call = endpoint.register(registerRequest);
        call.enqueue(getCallback(listener));
    }

    @Override
    public void login(LoginRequest loginRequest, RequestResponseListener<User> listener) {
        final Call<LoginResponse> call = endpoint.login(loginRequest);
        call.enqueue(getCallback(listener));
    }

    @Override
    public User getUser() {
        return null;
    }

    @NotNull
    private Callback<LoginResponse> getCallback(RequestResponseListener<User> listener) {
        return new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        listener.onSuccess(ResponseConverter.fromLoginResponse(response.body()));
                    } else {
                        listener.onEmpty();
                    }
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                listener.onError(throwable.getMessage());
            }
        };
    }
}
