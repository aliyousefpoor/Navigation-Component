package com.example.bottomnavigation.moretab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;

public class MoreViewModelFactory implements ViewModelProvider.Factory {
    private UserLocaleDataSourceImpl userLocaleDataSource;

    public MoreViewModelFactory(UserLocaleDataSourceImpl userLocaleDataSource) {
        this.userLocaleDataSource = userLocaleDataSource;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoreViewModel.class)) {
            return (T) new MoreViewModel(userLocaleDataSource);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
