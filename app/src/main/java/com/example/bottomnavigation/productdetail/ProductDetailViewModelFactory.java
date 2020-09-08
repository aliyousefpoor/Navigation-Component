package com.example.bottomnavigation.productdetail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.remote.ProductDetailRemoteDataSource;

public class ProductDetailViewModelFactory implements ViewModelProvider.Factory {
    private ProductDetailRemoteDataSource productDetailRemoteDataSource;


    public ProductDetailViewModelFactory(ProductDetailRemoteDataSource productDetailRemoteDataSource) {
        this.productDetailRemoteDataSource = productDetailRemoteDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductDetailViewModel.class)){
            return (T) new ProductDetailViewModel(productDetailRemoteDataSource);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
