package com.example.bottomnavigation.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.remote.LoginStepTwoRemoteDataSource;

public class LoginStepTwoViewModelFactory implements ViewModelProvider.Factory {
    private LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource;


    public LoginStepTwoViewModelFactory( LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource){
        this.loginStepTwoRemoteDataSource=loginStepTwoRemoteDataSource;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginStepTwoViewModel.class)){
            return (T) new LoginStepTwoViewModel(loginStepTwoRemoteDataSource);
        }
    throw new IllegalArgumentException("UnKnown Class");
    }
}
