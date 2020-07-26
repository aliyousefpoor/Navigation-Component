package com.example.bottomnavigation.data.datasource;


import com.example.bottomnavigation.data.model.VerificationResponseBody;

public interface LoginVerificationDataSource {
    void postCode(String number, String androidId, String code ,DataSourceListener<VerificationResponseBody> dataSourceListener);
}
