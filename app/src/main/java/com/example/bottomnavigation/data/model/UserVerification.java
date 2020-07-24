package com.example.bottomnavigation.data.model;

public class UserVerification {

    private String mobile;
    private String device_id;
    private String verification_code;

    public UserVerification(String mobile, String device_id, String verification_code) {
        this.mobile = mobile;
        this.device_id = device_id;
        this.verification_code = verification_code;
    }

    public String getMobile() {
        return mobile;
    }

    public String getDevice_id() {
        return device_id;
    }

    public String getVerification_code() {
        return verification_code;
    }
}
