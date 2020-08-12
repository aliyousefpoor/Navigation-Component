package com.example.bottomnavigation.moretab.di;


import com.example.bottomnavigation.data.repository.UserRepository;
import com.example.bottomnavigation.moretab.MoreViewModelFactory;
import com.example.bottomnavigation.moretab.profilefragment.ProfileFragment;
import com.example.bottomnavigation.moretab.profilefragment.ProfileViewModelFactory;


public class MoreModule {





    public static MoreViewModelFactory provideMoreViewModelFactory(UserRepository userRepository){
        return new MoreViewModelFactory(userRepository);
    }

    public static ProfileViewModelFactory provideProfileViewModelFactory(UserRepository userRepository){
        return new ProfileViewModelFactory(userRepository);
    }

    public static ProfileFragment provideProfileFragment(){
        return new ProfileFragment();
    }

}
