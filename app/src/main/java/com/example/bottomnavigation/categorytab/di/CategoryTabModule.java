package com.example.bottomnavigation.categorytab.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.categorytab.CategoryViewModelFactory;
import com.example.bottomnavigation.categorytab.ProductListViewModelFactory;
import com.example.bottomnavigation.data.datasource.remote.CategoryRemoteDataSource;
import com.example.bottomnavigation.data.datasource.remote.ProductListRemoteDataSource;

public class CategoryTabModule {
    public static CategoryRemoteDataSource provideCategorySource(ApiService apiService) {
        return new CategoryRemoteDataSource(apiService);
    }

    public static CategoryViewModelFactory provideCategoryViewModelFactory(CategoryRemoteDataSource categoryRemoteDataSource) {
        return new CategoryViewModelFactory(categoryRemoteDataSource);
    }

    public static ProductListRemoteDataSource provideProductListRemoteDataSource(ApiService apiService) {
        return new ProductListRemoteDataSource(apiService);
    }

    public static ProductListViewModelFactory provideProductListViewModelFactory(ProductListRemoteDataSource productListRemoteDataSource) {
        return new ProductListViewModelFactory(productListRemoteDataSource);
    }
}
