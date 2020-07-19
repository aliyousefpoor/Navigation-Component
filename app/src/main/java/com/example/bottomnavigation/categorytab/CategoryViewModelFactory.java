package com.example.bottomnavigation.categorytab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.CategorySource;

public class CategoryViewModelFactory implements ViewModelProvider.Factory {
    private CategorySource categorySource = new CategorySource();

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CategoryViewModel.class)){
            return (T) new CategoryViewModel(categorySource);
        }
        throw new IllegalArgumentException("Unknown Class");
    }
}
