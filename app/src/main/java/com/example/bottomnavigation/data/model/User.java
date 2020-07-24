package com.example.bottomnavigation.data.model;

public class User {

    private String  mobile;
    private String device_id;
    private String device_model;
    private String device_os;

    public User(String mobile, String device_id, String device_model, String device_os) {
        this.mobile = mobile;
        this.device_id = device_id;
        this.device_model = device_model;
        this.device_os = device_os;
    }


    public String getMobile() {
        return mobile;
    }

    public String getDevice_id() {
        return device_id;
    }

    public String getDevice_model() {
        return device_model;
    }

    public String getDevice_os() {
        return device_os;
    }
}
