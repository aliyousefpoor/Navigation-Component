package com.example.bottomnavigation.moretab.profilefragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.repository.LoginRepository;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private LoginRepository loginRepository;
    public LoginViewModelFactory(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)){
            return (T) new LoginViewModel(loginRepository);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
