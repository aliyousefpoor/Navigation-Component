package com.example.bottomnavigation.data.model;


public class UpdateProfile {
    private String nickname;
    private String date_of_birth;
    private String gender;
    private Object avatar;


    public UpdateProfile(String nickname, String date_of_birth, String gender) {
        this.nickname = nickname;
        this.date_of_birth = date_of_birth;
        this.gender = gender;

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

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(Object avatar) {
        this.avatar = avatar;
    }
}
