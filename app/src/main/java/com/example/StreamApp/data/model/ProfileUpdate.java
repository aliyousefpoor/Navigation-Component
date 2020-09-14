package com.example.StreamApp.data.model;

public class ProfileUpdate {
    private String nickname;
    private String date_of_birth;
    private String gender;
    private String token;

    public ProfileUpdate() {


    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRequestToken() {
        return "Token " + token;
    }

}
