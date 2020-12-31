package com.shiroecreative.todolist.data.request_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyRequest {
    @SerializedName("token")
    @Expose
    private String token;

    public VerifyRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
