package com.example.bottomnavigation.data.model;

public class LoginStepOneResponseBody {
    private String message;
    private String nickname;

    public LoginStepOneResponseBody(String message, String nickname){
        this.message=message;
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public String getNickname() {
        return nickname;
    }
}
