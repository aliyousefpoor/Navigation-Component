package com.example.bottomnavigation.moretab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.repository.IsLoginRepository;
import com.example.bottomnavigation.data.repository.LoginRepository;

public class MoreViewModelFactory implements ViewModelProvider.Factory {
    private IsLoginRepository isLoginRepository;
    public MoreViewModelFactory(IsLoginRepository isLoginRepository){
        this.isLoginRepository=isLoginRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoreViewModel.class)){
            return (T) new MoreViewModel(isLoginRepository);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
