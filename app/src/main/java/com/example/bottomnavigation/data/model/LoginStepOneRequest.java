package com.example.bottomnavigation.data.model;

public class LoginStepOneRequest {

    private String mobile;
    private String device_id;
    private String device_model;
    private String device_os;

    public LoginStepOneRequest() {
    }

    public LoginStepOneRequest(String mobile, String device_id, String device_model, String device_os) {
        this.mobile = mobile;
        this.device_id = device_id;
        this.device_model = device_model;
        this.device_os = device_os;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }


    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getDevice_os() {
        return device_os;
    }

    public void setDevice_os(String device_os) {
        this.device_os = device_os;
    }
}
