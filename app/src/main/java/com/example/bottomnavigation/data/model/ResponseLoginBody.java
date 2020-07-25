package com.example.bottomnavigation.data.model;

public class ResponseLoginBody {
    private String message;
    private String nickname;

    public ResponseLoginBody(String message, String nickname){
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
