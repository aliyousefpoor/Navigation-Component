package com.example.bottomnavigation.data.repository;

import android.content.Context;

import com.example.bottomnavigation.data.local.UserDataSource;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.moretab.UserInformationListener;

public class IsLoginRepository {
    private UserDataSource userDataSource;

    public  IsLoginRepository(UserDataSource userDataSource){
        this.userDataSource=userDataSource;
    }

    public void saveUser(User user, Context context){
        userDataSource.saveUser(user,context);
    }

    public void getUser(Context context, UserInformationListener userInformationListener){
        userDataSource.getUser(context,userInformationListener);
    }
}
