package com.example.bottomnavigation.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IspData {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("net_name")
    @Expose
    private String netName;
    @SerializedName("net_name_custom")
    @Expose
    private String netNameCustom;
    @SerializedName("logo")
    @Expose
    private Object logo;
    @SerializedName("color1")
    @Expose
    private String color1;
    @SerializedName("color2")
    @Expose
    private String color2;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public String getNetNameCustom() {
        return netNameCustom;
    }

    public void setNetNameCustom(String netNameCustom) {
        this.netNameCustom = netNameCustom;
    }

    public Object getLogo() {
        return logo;
    }

    public void setLogo(Object logo) {
        this.logo = logo;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }
}
