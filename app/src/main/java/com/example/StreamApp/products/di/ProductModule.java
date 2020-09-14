package com.example.StreamApp.products.di;

import com.example.StreamApp.ApiService;
import com.example.StreamApp.data.datasource.remote.ProductDetailRemoteDataSource;
import com.example.StreamApp.data.datasource.remote.ProductListRemoteDataSource;
import com.example.StreamApp.productdetail.ProductDetailViewModelFactory;
import com.example.StreamApp.products.ProductListViewModelFactory;

public class ProductModule {
    public static ProductListRemoteDataSource provideProductListRemoteDataSource(ApiService apiService) {
        return new ProductListRemoteDataSource(apiService);
    }

    public static ProductListViewModelFactory provideProductListViewModelFactory(ProductListRemoteDataSource productListRemoteDataSource) {
        return new ProductListViewModelFactory(productListRemoteDataSource);
    }
    public static ProductDetailRemoteDataSource provideProductDetailRemoteDataSource(ApiService apiService){
        return new ProductDetailRemoteDataSource(apiService);
    }
    public static ProductDetailViewModelFactory provideProductDetailViewModelFactory(ProductDetailRemoteDataSource productDetailRemoteDataSource){
        return new ProductDetailViewModelFactory(productDetailRemoteDataSource);
    }
}
