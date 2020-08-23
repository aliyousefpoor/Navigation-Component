package com.example.bottomnavigation.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.datasource.remote.LoginStepOneRemoteDataSource;
import com.example.bottomnavigation.data.datasource.remote.LoginStepTwoRemoteDataSource;


public class LoginSharedViewModelFactory implements ViewModelProvider.Factory {
    private LoginStepOneRemoteDataSource loginStepOneRemoteDataSource;
    private LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource;
    private UserLocaleDataSourceImpl userLocaleDataSource;

    public LoginSharedViewModelFactory(LoginStepOneRemoteDataSource loginStepOneRemoteDataSource
            , LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource, UserLocaleDataSourceImpl userLocaleDataSource) {
        this.loginStepOneRemoteDataSource = loginStepOneRemoteDataSource;
        this.loginStepTwoRemoteDataSource = loginStepTwoRemoteDataSource;
        this.userLocaleDataSource = userLocaleDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginSharedViewModel.class)) {
            return (T) new LoginSharedViewModel(loginStepOneRemoteDataSource, loginStepTwoRemoteDataSource,userLocaleDataSource);
        } else {
            throw new IllegalArgumentException("UnKnown Class");
        }
    }
}
