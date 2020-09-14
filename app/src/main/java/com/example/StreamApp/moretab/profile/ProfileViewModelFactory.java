package com.example.StreamApp.moretab.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.StreamApp.data.repository.ProfileRepository;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {
    private ProfileRepository profileRepository;
    public ProfileViewModelFactory(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileViewModel.class)){
            return (T) new ProfileViewModel(profileRepository);
        }
        throw new IllegalArgumentException("UnKnown Class");
    }
}
