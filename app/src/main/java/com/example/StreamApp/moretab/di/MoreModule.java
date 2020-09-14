package com.example.StreamApp.moretab.di;


import com.example.StreamApp.data.repository.ProfileRepository;
import com.example.StreamApp.moretab.profile.ProfileViewModelFactory;


public class MoreModule {

    public static ProfileViewModelFactory provideProfileViewModelFactory(ProfileRepository profileRepository){
        return new ProfileViewModelFactory(profileRepository);
    }

}
