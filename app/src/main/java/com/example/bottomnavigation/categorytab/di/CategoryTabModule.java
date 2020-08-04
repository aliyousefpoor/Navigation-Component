package com.example.bottomnavigation.categorytab.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.remote.CategoryRemoteDataSource;

public class CategoryTabModule {
    public static CategoryRemoteDataSource provideCategorySource(ApiService apiService){
        return new CategoryRemoteDataSource(apiService);
    }
}
