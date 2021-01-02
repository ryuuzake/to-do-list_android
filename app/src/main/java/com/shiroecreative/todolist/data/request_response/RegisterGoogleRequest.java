package com.shiroecreative.todolist.data.request_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterGoogleRequest {
    @SerializedName("code")
    @Expose
    private String code;

    public RegisterGoogleRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
