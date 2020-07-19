package com.example.bottomnavigation.hometab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.datasource.StoreSource;


public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private StoreSource storeSource;

    public HomeViewModelFactory(StoreSource storeSource) {
        this.storeSource = storeSource;
    }

    @NonNull
    @Override

    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(storeSource);
        }
        throw new IllegalArgumentException("Unknown Class");

    }
}