package com.example.bottomnavigation.data.model;

public class LoginStepTwoRequest {

    private String mobile;
    private String device_id;
    private String verification_code;

    public LoginStepTwoRequest() {
    }

    public LoginStepTwoRequest(String mobile, String device_id, String verification_code) {
        this.mobile = mobile;
        this.device_id = device_id;
        this.verification_code = verification_code;
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

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }
}
