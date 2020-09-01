package com.example.bottomnavigation.moretab.di;


import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.repository.ProfileRepository;
import com.example.bottomnavigation.moretab.profile.ProfileViewModelFactory;


public class MoreModule {

    public static ProfileViewModelFactory provideProfileViewModelFactory(ProfileRepository profileRepository){
        return new ProfileViewModelFactory(profileRepository);
    }

}
