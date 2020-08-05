package com.example.bottomnavigation.moretab.di;


import com.example.bottomnavigation.data.local.UserDataSource;
import com.example.bottomnavigation.data.repository.IsLoginRepository;
import com.example.bottomnavigation.data.repository.LoginRepository;
import com.example.bottomnavigation.moretab.MoreViewModelFactory;
import com.example.bottomnavigation.moretab.profilefragment.ProfileFragment;
import com.example.bottomnavigation.moretab.profilefragment.ProfileViewModel;
import com.example.bottomnavigation.moretab.profilefragment.ProfileViewModelFactory;


public class MoreModule {





    public static MoreViewModelFactory provideMoreViewModelFactory(IsLoginRepository isLoginRepository){
        return new MoreViewModelFactory(isLoginRepository);
    }

    public static ProfileViewModelFactory provideProfileViewModelFactory(IsLoginRepository isLoginRepository){
        return new ProfileViewModelFactory(isLoginRepository);
    }

    public static ProfileFragment provideProfileFragment(){
        return new ProfileFragment();
    }

}
