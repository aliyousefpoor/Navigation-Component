package com.example.StreamApp.hometab.di;

import com.example.StreamApp.ApiService;
import com.example.StreamApp.data.datasource.remote.HomeRemoteDataSource;
import com.example.StreamApp.hometab.HomeViewModelFactory;


public class HomeTabModule {
    public static HomeRemoteDataSource provideHomeSource(ApiService apiService) {
        return new HomeRemoteDataSource(apiService);
    }

    public static HomeViewModelFactory provideHomeViewModelFactory(HomeRemoteDataSource homeRemoteDataSource){
    return new HomeViewModelFactory(homeRemoteDataSource);
    }
}