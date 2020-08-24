package com.example.bottomnavigation.data.repository;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.datasource.local.database.IsLoginListener;
import com.example.bottomnavigation.data.datasource.remote.LoginStepOneRemoteDataSource;
import com.example.bottomnavigation.data.datasource.remote.LoginStepTwoRemoteDataSource;
import com.example.bottomnavigation.data.model.LoginStepOneRequest;
import com.example.bottomnavigation.data.model.LoginStepOneResponse;
import com.example.bottomnavigation.data.model.LoginStepTwoRequest;
import com.example.bottomnavigation.data.model.LoginStepTwoResponse;

public class LoginRepository {
    private LoginStepOneRemoteDataSource loginStepOneRemoteDataSource;
    private LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource;
    private UserLocaleDataSourceImpl userLocaleDataSource;

    public LoginRepository(LoginStepOneRemoteDataSource loginStepOneRemoteDataSource
            , LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource, UserLocaleDataSourceImpl userLocaleDataSource) {
        this.loginStepOneRemoteDataSource = loginStepOneRemoteDataSource;
        this.loginStepTwoRemoteDataSource = loginStepTwoRemoteDataSource;
        this.userLocaleDataSource = userLocaleDataSource;
    }

    public void loginStepOne(LoginStepOneRequest loginStepOneRequest, DataSourceListener<LoginStepOneResponse> dataSourceListener) {
        loginStepOneRemoteDataSource.loginStepOne(loginStepOneRequest, dataSourceListener);
    }

    public void loginStepTwo(LoginStepTwoRequest loginStepTwoRequest, final DataSourceListener<LoginStepTwoResponse> dataSourceListener) {
        loginStepTwoRemoteDataSource.loginStepTwo(loginStepTwoRequest, new DataSourceListener<LoginStepTwoResponse>() {
            @Override
            public void onResponse(LoginStepTwoResponse response) {
                dataSourceListener.onResponse(response);
                loginUser(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                dataSourceListener.onFailure(throwable);
            }
        });
    }

    public void loginUser(LoginStepTwoResponse loginStepTwoResponse) {
        userLocaleDataSource.loginUser(loginStepTwoResponse);
    }

    public void isLogin(IsLoginListener isLoginListener){
        userLocaleDataSource.isLogin(isLoginListener);
    }
}
