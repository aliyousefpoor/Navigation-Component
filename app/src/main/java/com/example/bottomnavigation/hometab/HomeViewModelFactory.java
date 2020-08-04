package com.example.bottomnavigation.hometab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.remote.HomeRemoteDataSource;


public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private HomeRemoteDataSource homeRemoteDataSource;

    public HomeViewModelFactory(HomeRemoteDataSource homeRemoteDataSource) {
        this.homeRemoteDataSource = homeRemoteDataSource;
    }

    @NonNull
    @Override

    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(homeRemoteDataSource);
        }
        throw new IllegalArgumentException("Unknown Class");

    }
}