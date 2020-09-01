package com.example.bottomnavigation.data.model;

public class LoginStepOneResponse {
    private String message;
    private String nickname;

    public LoginStepOneResponse(String message, String nickname) {
        this.message = message;
        this.nickname = nickname;
    }

    public String getMessage() {
        return message;
    }

    public String getNickname() {
        return nickname;
    }
}
