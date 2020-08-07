package com.example.bottomnavigation.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RemoteUser {
    @SerializedName("credit")
    @Expose
    private List<Credit> credits = null;
    @SerializedName("magic_credit")
    @Expose
    private List<Credit> magic_credits = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("friends")
    @Expose
    private ArrayList friends;
    @SerializedName("error")
    @Expose
    private String error;

    @SerializedName("nickname")
    @Expose
    private String nickName;
    @SerializedName("date_of_birth")
    @Expose
    private String birthdayDate;
    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("has_supercollection")
    @Expose
    private String hasSuperCollection;
    @SerializedName("isp_data")
    @Expose
    private List<IspData> ispData;

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public List<Credit> getMagic_credits() {
        return magic_credits;
    }

    public void setMagic_credits(List<Credit> magic_credits) {
        this.magic_credits = magic_credits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList getFriends() {
        return friends;
    }

    public void setFriends(ArrayList friends) {
        this.friends = friends;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(String birthdayDate) {
        this.birthdayDate = birthdayDate;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHasSuperCollection() {
        return hasSuperCollection;
    }

    public void setHasSuperCollection(String hasSuperCollection) {
        this.hasSuperCollection = hasSuperCollection;
    }

    public List<IspData> getIspData() {
        return ispData;
    }

    public void setIspData(List<IspData> ispData) {
        this.ispData = ispData;
    }
}
