package com.example.bottomnavigation.data.repository;


import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.LoginStepOne;
import com.example.bottomnavigation.data.model.LoginStepTwo;

import com.example.bottomnavigation.data.remote.LoginRemoteDataSource;

import com.example.bottomnavigation.data.remote.VerificationRemoteDataSource;
import com.example.bottomnavigation.data.model.LoginResponseBody;

import com.example.bottomnavigation.data.model.VerificationResponseBody;


public class LoginRepository {
    private static final String TAG = "LoginRepository";

    private LoginRemoteDataSource loginRemoteDataSource;
    private VerificationRemoteDataSource verificationRemoteDataSource;


    public LoginRepository(LoginRemoteDataSource loginRemoteDataSource) {
        this.loginRemoteDataSource = loginRemoteDataSource;
    }

    public LoginRepository(VerificationRemoteDataSource verificationRemoteDataSource) {
        this.verificationRemoteDataSource = verificationRemoteDataSource;
    }




    public void loginStep1(LoginStepOne loginStepOne, DataSourceListener<LoginResponseBody> dataSourceListener) {
        loginRemoteDataSource.loginStepOne(loginStepOne, dataSourceListener);

    }

    public void loginStep2(LoginStepTwo loginStepTwo, DataSourceListener<VerificationResponseBody> dataSourceListener) {
        verificationRemoteDataSource.loginStepTwo(loginStepTwo, dataSourceListener);

    }




}
