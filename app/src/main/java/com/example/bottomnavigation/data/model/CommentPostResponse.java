package com.example.bottomnavigation.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentPostResponse {
    @SerializedName("error")
    @Expose
    private int error;
    @SerializedName("message")
    @Expose
    private String message;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
