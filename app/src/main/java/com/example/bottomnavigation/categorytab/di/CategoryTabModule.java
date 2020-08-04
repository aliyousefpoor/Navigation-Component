package com.example.bottomnavigation.categorytab.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.CategoryRemoteDataSource;

public class CategoryTabModule {
    public static CategoryRemoteDataSource provideCategorySource(ApiService apiService){
        return new CategoryRemoteDataSource(apiService);
    }
}
