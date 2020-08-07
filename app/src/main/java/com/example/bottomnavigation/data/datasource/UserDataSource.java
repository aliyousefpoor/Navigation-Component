package com.example.bottomnavigation.data.datasource;

import android.content.Context;

import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.moretab.UserInformationListener;


public interface UserDataSource {


    void saveUser(User user , Context context);
    void getUser(Context context , UserInformationListener userInformationListener);
}
