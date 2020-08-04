package com.example.bottomnavigation.data.repository;

import android.content.Context;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.LoginRemoteDataSource;
import com.example.bottomnavigation.data.datasource.UserLocalDataSource;
import com.example.bottomnavigation.data.datasource.VerificationRemoteDataSource;
import com.example.bottomnavigation.data.model.LoginResponseBody;

import com.example.bottomnavigation.data.model.VerificationResponseBody;
import com.example.bottomnavigation.moretab.UserInformationListener;


public class LoginRepository {
    private static final String TAG = "LoginRepository";

    private LoginRemoteDataSource loginRemoteDataSource;
    private VerificationRemoteDataSource verificationRemoteDataSource;
    private UserLocalDataSource userLocalDataSource;


    public LoginRepository(LoginRemoteDataSource loginRemoteDataSource) {
        this.loginRemoteDataSource = loginRemoteDataSource;
    }

    public LoginRepository(VerificationRemoteDataSource verificationRemoteDataSource) {
        this.verificationRemoteDataSource = verificationRemoteDataSource;
    }
    public LoginRepository(UserLocalDataSource userLocalDataSource){
        this.userLocalDataSource = userLocalDataSource;
    }


    public void repoPostNumber(String number, String androidId, String device_model, String device_os, DataSourceListener<LoginResponseBody> dataSourceListener) {
        loginRemoteDataSource.postNumber(number, androidId, device_model, device_os, dataSourceListener);

    }

    public void repoPostCode(String number, String androidId, String code, DataSourceListener<VerificationResponseBody> dataSourceListener){
        verificationRemoteDataSource.postCode(number,androidId,code,dataSourceListener);

    }

    public void saveUser(int id, String token , String name, String date , String gender , Context context){
        userLocalDataSource.saveInformation(id,token,name ,date,gender,context);
    }

    public void getInformation(Context context , UserInformationListener userInformationListener){
        userLocalDataSource.getUserInformation(context , userInformationListener);
    }

}
