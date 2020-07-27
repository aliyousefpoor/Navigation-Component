package com.example.bottomnavigation.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class VerificationResponseBody implements Parcelable {
    private int user_id;
    private String token;
    private String message;
    private String nickname;
    private String fino_token;

    public VerificationResponseBody(int user_id, String token, String message, String nickname, String fino_token) {
        this.user_id = user_id;
        this.token = token;
        this.message = message;
        this.nickname = nickname;
        this.fino_token = fino_token;
    }

    protected VerificationResponseBody(Parcel in) {
        user_id = in.readInt();
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

    public int getUser_id() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(user_id);
        dest.writeString(token);
        dest.writeString(message);
        dest.writeString(nickname);
        dest.writeString(fino_token);
    }
}
