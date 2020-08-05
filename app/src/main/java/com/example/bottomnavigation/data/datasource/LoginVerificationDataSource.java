package com.example.bottomnavigation.data.datasource;


import com.example.bottomnavigation.data.model.LoginStepTwo;
import com.example.bottomnavigation.data.model.VerificationResponseBody;

public interface LoginVerificationDataSource {
    void loginStepTwo(LoginStepTwo loginStepTwo, DataSourceListener<VerificationResponseBody> dataSourceListener);
}
