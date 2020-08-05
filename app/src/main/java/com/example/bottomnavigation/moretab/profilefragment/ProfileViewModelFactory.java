package com.example.bottomnavigation.moretab.profilefragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bottomnavigation.data.repository.IsLoginRepository;
import com.example.bottomnavigation.data.repository.LoginRepository;
import com.example.bottomnavigation.moretab.profilefragment.ProfileViewModel;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {
    private IsLoginRepository isLoginRepository;
    public ProfileViewModelFactory(IsLoginRepository isLoginRepository){
        this.isLoginRepository=isLoginRepository ;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileViewModel.class)){
            return (T) new ProfileViewModel(isLoginRepository);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
