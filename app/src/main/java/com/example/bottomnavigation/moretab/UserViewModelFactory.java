package com.example.bottomnavigation.moretab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.categorytab.CategoryViewModel;
import com.example.bottomnavigation.data.datasource.UserSource;

public class UserViewModelFactory implements ViewModelProvider.Factory {
    private UserSource userSource;
    public UserViewModelFactory(UserSource userSource){
        this.userSource=userSource;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)){
            return (T) new UserViewModel(userSource);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
