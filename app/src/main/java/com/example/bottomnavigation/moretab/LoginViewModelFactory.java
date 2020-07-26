package com.example.bottomnavigation.moretab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.example.bottomnavigation.data.datasource.LoginSource;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private LoginSource loginSource;
    public LoginViewModelFactory(LoginSource loginSource){
        this.loginSource = loginSource;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(loginSource);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
