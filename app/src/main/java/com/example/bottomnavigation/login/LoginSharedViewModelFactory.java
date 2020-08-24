package com.example.bottomnavigation.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.datasource.remote.LoginStepOneRemoteDataSource;
import com.example.bottomnavigation.data.datasource.remote.LoginStepTwoRemoteDataSource;
import com.example.bottomnavigation.data.repository.LoginRepository;


public class LoginSharedViewModelFactory implements ViewModelProvider.Factory {
    private LoginRepository loginRepository;

    public LoginSharedViewModelFactory(LoginRepository loginRepository){
        this.loginRepository =loginRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginSharedViewModel.class)) {
            return (T) new LoginSharedViewModel(loginRepository);
        } else {
            throw new IllegalArgumentException("UnKnown Class");
        }
    }
}
