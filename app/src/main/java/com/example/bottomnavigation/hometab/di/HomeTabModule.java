package com.example.bottomnavigation.hometab.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.HomeSource;


public class HomeTabModule {
    public static HomeSource provideHomeSource(ApiService apiService) {
        return new HomeSource(apiService);
    }
}
