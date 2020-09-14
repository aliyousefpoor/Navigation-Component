package com.example.StreamApp.categorytab.di;

import com.example.StreamApp.ApiService;
import com.example.StreamApp.categorytab.CategoryViewModelFactory;
import com.example.StreamApp.data.datasource.remote.CategoryRemoteDataSource;

public class CategoryTabModule {
    public static CategoryRemoteDataSource provideCategorySource(ApiService apiService) {
        return new CategoryRemoteDataSource(apiService);
    }

    public static CategoryViewModelFactory provideCategoryViewModelFactory(CategoryRemoteDataSource categoryRemoteDataSource) {
        return new CategoryViewModelFactory(categoryRemoteDataSource);
    }
}
