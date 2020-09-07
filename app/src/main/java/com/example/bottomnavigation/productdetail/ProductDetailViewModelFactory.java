package com.example.bottomnavigation.productdetail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.datasource.remote.ProductDetailRemoteDataSource;

public class ProductDetailViewModelFactory implements ViewModelProvider.Factory {
    private ProductDetailRemoteDataSource productDetailRemoteDataSource;
    private UserLocaleDataSourceImpl userLocaleDataSource;

    public ProductDetailViewModelFactory(ProductDetailRemoteDataSource productDetailRemoteDataSource,UserLocaleDataSourceImpl userLocaleDataSource) {
        this.productDetailRemoteDataSource = productDetailRemoteDataSource;
        this.userLocaleDataSource=userLocaleDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductDetailViewModel.class)){
            return (T) new ProductDetailViewModel(productDetailRemoteDataSource,userLocaleDataSource);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
