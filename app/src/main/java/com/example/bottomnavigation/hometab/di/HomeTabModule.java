package com.example.bottomnavigation.hometab.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.remote.HomeRemoteDataSource;
import com.example.bottomnavigation.hometab.HomeViewModelFactory;


public class HomeTabModule {
    public static HomeRemoteDataSource provideHomeSource(ApiService apiService) {
        return new HomeRemoteDataSource(apiService);
    }

    public static HomeViewModelFactory provideHomeViewModelFactory(HomeRemoteDataSource homeRemoteDataSource){
    return new HomeViewModelFactory(homeRemoteDataSource);
    }
}