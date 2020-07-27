package com.example.bottomnavigation.data.repository;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.LoginRemoteDataSource;
import com.example.bottomnavigation.data.datasource.VerificationRemoteDataSource;
import com.example.bottomnavigation.data.model.LoginResponseBody;
import com.example.bottomnavigation.data.model.VerificationResponseBody;

public class LoginRepository {
    private static final String TAG = "LoginRepository";

    private LoginRemoteDataSource loginRemoteDataSource;
    private VerificationRemoteDataSource verificationRemoteDataSource;


    public LoginRepository(LoginRemoteDataSource loginRemoteDataSource){
        this.loginRemoteDataSource = loginRemoteDataSource;
    }

    public LoginRepository(VerificationRemoteDataSource verificationRemoteDataSource){
        this.verificationRemoteDataSource = verificationRemoteDataSource;
    }



    public void repoPostNumber(String number, String androidId, String device_model , String device_os, DataSourceListener<LoginResponseBody> dataSourceListener){
        loginRemoteDataSource.postNumber(number,androidId,device_model,device_os,dataSourceListener);

    }

    public void repoPostCode(String number, String androidId, String code, DataSourceListener<VerificationResponseBody> dataSourceListener){
        verificationRemoteDataSource.postCode(number,androidId,code,dataSourceListener);
    }



}
