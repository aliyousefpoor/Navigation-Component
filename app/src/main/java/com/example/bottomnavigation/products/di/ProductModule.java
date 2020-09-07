package com.example.bottomnavigation.products.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.datasource.remote.ProductDetailRemoteDataSource;
import com.example.bottomnavigation.data.datasource.remote.ProductListRemoteDataSource;
import com.example.bottomnavigation.data.datasource.remote.SendCommentRemoteDataSource;
import com.example.bottomnavigation.productdetail.CommentViewModelFactory;
import com.example.bottomnavigation.productdetail.ProductDetailViewModelFactory;
import com.example.bottomnavigation.products.ProductListViewModelFactory;

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
    public static ProductDetailViewModelFactory provideProductDetailViewModelFactory(ProductDetailRemoteDataSource productDetailRemoteDataSource
    , UserLocaleDataSourceImpl userLocaleDataSource){
        return new ProductDetailViewModelFactory(productDetailRemoteDataSource,userLocaleDataSource);
    }
    public static SendCommentRemoteDataSource provideSendCommentRemoteDataSource(ApiService apiService){
        return new SendCommentRemoteDataSource(apiService);
    }

    public static CommentViewModelFactory provideCommentViewModelFactory(ApiService apiService){
        return new CommentViewModelFactory(provideSendCommentRemoteDataSource(apiService));
    }
}
