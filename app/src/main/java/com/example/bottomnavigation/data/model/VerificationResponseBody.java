package com.example.bottomnavigation.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class VerificationResponseBody implements Parcelable {
    private int userId;
    private String token;
    private String message;
    private String nickname;
    private String fino_token;

    public VerificationResponseBody(int userId, String token, String message, String nickname, String fino_token) {
        this.userId = userId;
        this.token = token;
        this.message = message;
        this.nickname = nickname;
        this.fino_token = fino_token;
    }

    protected VerificationResponseBody(Parcel in) {
        userId = in.readInt();
        token = in.readString();
        message = in.readString();
        nickname = in.readString();
        fino_token = in.readString();
    }

    public static final Creator<VerificationResponseBody> CREATOR = new Creator<VerificationResponseBody>() {
        @Override
        public VerificationResponseBody createFromParcel(Parcel in) {
            return new VerificationResponseBody(in);
        }

        @Override
        public VerificationResponseBody[] newArray(int size) {
            return new VerificationResponseBody[size];
        }
    };

    public int getUserId() {
        return userId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(token);
        dest.writeString(message);
        dest.writeString(nickname);
        dest.writeString(fino_token);
    }
}
