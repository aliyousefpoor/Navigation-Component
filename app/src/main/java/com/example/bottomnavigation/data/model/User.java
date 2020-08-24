package com.example.bottomnavigation.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int userId;

    private String token;

    private String name;

    private String date;

    private String gender;

    private String avatar;

    public User() {
    }


    protected User(Parcel in) {
        userId = in.readInt();
        token = in.readString();
        name = in.readString();
        date = in.readString();
        gender = in.readString();
        avatar = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public String getRequestToken() {
        return "Token " + token;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(token);
        dest.writeString(name);
        dest.writeString(date);
        dest.writeString(gender);
        dest.writeString(avatar);
    }
}
