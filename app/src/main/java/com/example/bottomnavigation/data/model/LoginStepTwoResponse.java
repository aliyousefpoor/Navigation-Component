package com.example.bottomnavigation.data.model;


public class LoginStepTwoResponse {
    private int user_id;
    private String token;
    private String message;
    private String nickname;
    private String fino_token;

    public LoginStepTwoResponse(int user_id, String token, String message, String nickname, String fino_token) {
        this.user_id = user_id;
        this.token = token;
        this.message = message;
        this.nickname = nickname;
        this.fino_token = fino_token;
    }



    public int getUserId() {
        return user_id;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public String getNickname() {
        return nickname;
    }

    public String getFino_token() {
        return fino_token;
    }

}
