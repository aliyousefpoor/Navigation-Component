package com.example.bottomnavigation.data.model;

public class LoginResponseBody {
    private String message;
    private String nickname;

    public LoginResponseBody(String message, String nickname){
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
