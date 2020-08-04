package com.example.bottomnavigation.data.datasource;

import android.content.Context;

import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.moretab.UserInformationListener;


//rename
public interface LocalUserDataSource {

    //use model
    void saveInformation(User user , Context context);
    void getUserInformation(Context context , UserInformationListener userInformationListener);
}
