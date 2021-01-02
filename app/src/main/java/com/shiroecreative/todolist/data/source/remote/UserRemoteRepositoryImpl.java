package com.shiroecreative.todolist.data.source.remote;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.LoginRequest;
import com.shiroecreative.todolist.data.request_response.LoginResponse;
import com.shiroecreative.todolist.data.request_response.RegisterGoogleRequest;
import com.shiroecreative.todolist.data.request_response.RegisterRequest;
import com.shiroecreative.todolist.data.request_response.VerifyRequest;
import com.shiroecreative.todolist.utils.RequestResponseListener;
import com.shiroecreative.todolist.utils.UserUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRemoteRepositoryImpl extends RemoteRepository<UserEndpoint> implements UserRemoteRepository {

    public UserRemoteRepositoryImpl() {
        super(UserEndpoint.class);
    }

    @Override
    public void register(RegisterRequest registerRequest, RequestResponseListener<User> listener) {
        final Call<LoginResponse> call = endpoint.register(registerRequest);
        call.enqueue(new UserCallback<>(listener));
    }

    @Override
    public void login(LoginRequest loginRequest, RequestResponseListener<User> listener) {
        final Call<LoginResponse> call = endpoint.login(loginRequest);
        call.enqueue(new UserCallback<>(listener));
    }

    @Override
    public void checkToken(VerifyRequest verifyRequest, RequestResponseListener<Boolean> listener) {
        final Call<ResponseBody> call = endpoint.verifyToken(verifyRequest);
        call.enqueue(new UserCallback<>(listener));
    }

    @Override
    public void registerGoogleAccount(RegisterGoogleRequest registerGoogleRequest, RequestResponseListener<User> listener) {
        final Call<LoginResponse> call = endpoint.registerGoogleAccount(registerGoogleRequest);
        call.enqueue(new UserCallback<>(listener));
    }

    private static class UserCallback<T, U> implements Callback<T> {

        private RequestResponseListener<U> listener;

        private UserCallback(RequestResponseListener<U> listener) {
            this.listener = listener;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    T t = response.body();
                    if (t instanceof LoginResponse) {
                        listener.onSuccess((U) UserUtil.fromLoginResponse((LoginResponse) t));
                    } else {
                        listener.onSuccess((U) Boolean.TRUE);
                    }
                } else {
                    listener.onEmpty();
                }
            } else {
                listener.onError(response.message());
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable throwable) {
            listener.onError(throwable.getMessage());
        }
    }
}
