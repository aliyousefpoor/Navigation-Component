package com.example.bottomnavigation.hometab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.HomeSource;


public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private HomeSource homeSource;

    public HomeViewModelFactory(HomeSource homeSource) {
        this.homeSource = homeSource;
    }

    @NonNull
    @Override

    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(homeSource);
        }
        throw new IllegalArgumentException("Unknown Class");

    }
}