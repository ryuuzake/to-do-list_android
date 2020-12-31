package com.shiroecreative.todolist.data.source.remote;

import com.shiroecreative.todolist.data.request_response.LoginRequest;
import com.shiroecreative.todolist.data.request_response.LoginResponse;
import com.shiroecreative.todolist.data.request_response.RegisterRequest;
import com.shiroecreative.todolist.data.request_response.VerifyRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserEndpoint {
    String USER_ENDPOINT = "rest-auth";

    @POST(USER_ENDPOINT + "/login/")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST(USER_ENDPOINT + "/registration/")
    Call<LoginResponse> register(@Body RegisterRequest registerRequest);

    @POST(USER_ENDPOINT + "/verify/")
    Call<ResponseBody> verifyToken(@Body VerifyRequest verifyRequest);
}
