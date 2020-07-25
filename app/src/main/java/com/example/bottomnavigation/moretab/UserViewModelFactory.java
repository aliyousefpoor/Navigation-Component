package com.example.bottomnavigation.moretab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.example.bottomnavigation.data.datasource.LoginSource;

public class UserViewModelFactory implements ViewModelProvider.Factory {
    private LoginSource loginSource;
    public UserViewModelFactory(LoginSource loginSource){
        this.loginSource = loginSource;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)){
            return (T) new UserViewModel(loginSource);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
