package com.example.bottomnavigation.categorytab.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.CategorySource;

public class CategoryTabModule {
    public static CategorySource provideCategorySource(ApiService apiService){
        return new CategorySource(apiService);
    }
}
