package com.example.bottomnavigation.categorytab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.remote.CategoryRemoteDataSource;

public class CategoryViewModelFactory implements ViewModelProvider.Factory {
    private CategoryRemoteDataSource categoryRemoteDataSource;

    public CategoryViewModelFactory(CategoryRemoteDataSource categoryRemoteDataSource) {
        this.categoryRemoteDataSource = categoryRemoteDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CategoryViewModel.class)) {
            return (T) new CategoryViewModel(categoryRemoteDataSource);
        }
        throw new IllegalArgumentException("Unknown Class");
    }
}
