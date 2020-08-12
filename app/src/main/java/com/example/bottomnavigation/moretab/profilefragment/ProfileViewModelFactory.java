package com.example.bottomnavigation.moretab.profilefragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.repository.UserRepository;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {
    private UserRepository userRepository;
    public ProfileViewModelFactory(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileViewModel.class)){
            return (T) new ProfileViewModel(userRepository);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
