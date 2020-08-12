package com.example.bottomnavigation.moretab;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.repository.UserRepository;

public class MoreViewModelFactory implements ViewModelProvider.Factory {
    private UserRepository userRepository;
    public MoreViewModelFactory(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoreViewModel.class)){
            return (T) new MoreViewModel(userRepository);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
