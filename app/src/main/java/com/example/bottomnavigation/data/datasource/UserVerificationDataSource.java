package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.data.model.UserVerification;

public interface UserVerificationDataSource {
    void postCode(String number, String androidId, String code ,DataSourceListener<UserVerification> dataSourceListener);
}
