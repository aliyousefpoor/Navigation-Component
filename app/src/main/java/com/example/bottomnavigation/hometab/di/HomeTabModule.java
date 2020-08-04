package com.example.bottomnavigation.hometab.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.remote.HomeRemoteDataSource;


public class HomeTabModule {
    public static HomeRemoteDataSource provideHomeSource(ApiService apiService) {
        return new HomeRemoteDataSource(apiService);
    }
}
