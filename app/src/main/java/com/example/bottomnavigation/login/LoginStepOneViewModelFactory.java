package com.example.bottomnavigation.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.remote.LoginStepOneRemoteDataSource;

public class LoginStepOneViewModelFactory implements ViewModelProvider.Factory {
    private LoginStepOneRemoteDataSource loginStepOneRemoteDataSource;
    public LoginStepOneViewModelFactory(LoginStepOneRemoteDataSource loginStepOneRemoteDataSource){
        this.loginStepOneRemoteDataSource = loginStepOneRemoteDataSource;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginStepOneViewModel.class)){
            return (T) new LoginStepOneViewModel(loginStepOneRemoteDataSource);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
