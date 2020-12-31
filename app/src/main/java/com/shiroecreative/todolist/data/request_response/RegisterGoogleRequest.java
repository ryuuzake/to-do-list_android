package com.shiroecreative.todolist.data.request_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterGoogleRequest {
    @SerializedName("access_token")
    @Expose
    private String accessToken;

    public RegisterGoogleRequest(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
