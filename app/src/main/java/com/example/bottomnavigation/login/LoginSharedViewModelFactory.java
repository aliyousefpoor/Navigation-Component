package com.example.bottomnavigation.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.remote.LoginStepOneRemoteDataSource;
import com.example.bottomnavigation.data.datasource.remote.LoginStepTwoRemoteDataSource;


public class LoginSharedViewModelFactory implements ViewModelProvider.Factory {
    private LoginStepOneRemoteDataSource loginStepOneRemoteDataSource;
    private LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource;

    public LoginSharedViewModelFactory(LoginStepOneRemoteDataSource loginStepOneRemoteDataSource
            , LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource) {
        this.loginStepOneRemoteDataSource = loginStepOneRemoteDataSource;
        this.loginStepTwoRemoteDataSource = loginStepTwoRemoteDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginSharedViewModel.class)) {
            return (T) new LoginSharedViewModel(loginStepOneRemoteDataSource,loginStepTwoRemoteDataSource);
        }
        else {
            throw new IllegalArgumentException("UnKnown Class");
        }
    }
}
