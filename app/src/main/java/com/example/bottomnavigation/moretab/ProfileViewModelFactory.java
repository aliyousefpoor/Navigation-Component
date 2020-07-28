package com.example.bottomnavigation.moretab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.repository.LoginRepository;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {
    LoginRepository loginRepository;
    public ProfileViewModelFactory(LoginRepository loginRepository){
        this.loginRepository=loginRepository ;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileViewModel.class)){
            return (T) new ProfileViewModel(loginRepository);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
