package com.example.bottomnavigation.moretab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.repository.LoginRepository;

public class MoreViewModelFactory implements ViewModelProvider.Factory {
    private LoginRepository loginRepository;
    public MoreViewModelFactory(LoginRepository loginRepository){
        this.loginRepository=loginRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoreViewModel.class)){
            return (T) new MoreViewModel(loginRepository);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
