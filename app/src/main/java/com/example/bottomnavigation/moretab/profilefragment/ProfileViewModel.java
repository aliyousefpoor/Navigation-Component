package com.example.bottomnavigation.moretab.profilefragment;

import android.content.Context;

import androidx.lifecycle.ViewModel;


import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.repository.IsLoginRepository;


public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private IsLoginRepository isLoginRepository;

    public ProfileViewModel(IsLoginRepository isLoginRepository){
        this.isLoginRepository=isLoginRepository;
    }


    public void saveUser(User user, Context context){
        isLoginRepository.saveUser(user , context);
    }

}
