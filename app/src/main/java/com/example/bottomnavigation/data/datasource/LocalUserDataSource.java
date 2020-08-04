package com.example.bottomnavigation.data.datasource;

import android.content.Context;

import com.example.bottomnavigation.moretab.UserInformationListener;


public interface LocalUserDataSource {

    void saveInformation(int userId, String token , String name , String date , String gender , Context context);
    void getUserInformation(Context context , UserInformationListener userInformationListener);
}
