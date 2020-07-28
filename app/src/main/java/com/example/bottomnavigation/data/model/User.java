package com.example.bottomnavigation.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey()
    private int userId;

    @ColumnInfo(name = "token")
    private String token;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "gender")
    private String gender;


//    int user_id, String token, String name, Double date, String gender
    public User() {
//        this.user_id = user_id;
//        this.token = token;
//        this.name = name;
//        this.date = date;
//        this.gender = gender;
    }

//    @Ignore
//    public User(String name, double date, Gender gender) {
//        this.name = name;
//        this.date = date;
//        this.gender = gender;
//    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
