package com.example.StreamApp.products;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.StreamApp.data.datasource.remote.ProductListRemoteDataSource;

public class ProductListViewModelFactory implements ViewModelProvider.Factory {
    private ProductListRemoteDataSource productListRemoteDataSource;

    public ProductListViewModelFactory(ProductListRemoteDataSource productListRemoteDataSource) {
        this.productListRemoteDataSource = productListRemoteDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProductListViewModel.class)){
            return (T) new ProductListViewModel(productListRemoteDataSource);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
