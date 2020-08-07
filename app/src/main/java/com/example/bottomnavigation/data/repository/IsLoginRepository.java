package com.example.bottomnavigation.data.repository;

import android.content.Context;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.model.ProfileUpdate;
import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.model.UpdateResponseBody;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.remote.UserRemoteDataSource;
import com.example.bottomnavigation.moretab.UserInformationListener;

public class IsLoginRepository {
    private UserLocaleDataSourceImpl userLocaleDataSourceImpl;
    private UserRemoteDataSource userRemoteDataSource;

    public IsLoginRepository(UserLocaleDataSourceImpl userLocaleDataSourceImpl) {
        this.userLocaleDataSourceImpl = userLocaleDataSourceImpl;
    }

    public IsLoginRepository(UserRemoteDataSource userRemoteDataSource) {
        this.userRemoteDataSource = userRemoteDataSource;
    }

    public void saveUser(User user, Context context) {
        userLocaleDataSourceImpl.saveUser(user, context);
    }

    public void getUser(Context context, UserInformationListener userInformationListener) {
        userLocaleDataSourceImpl.getUser(context, userInformationListener);
    }

    public void updateProfile(ProfileUpdate profileUpdate, DataSourceListener<UpdateResponseBody> dataSourceListener){
        userRemoteDataSource.updateProfile(profileUpdate,dataSourceListener);
    }
    public void getProfile(String token,Context context, DataSourceListener<RemoteUser> dataSourceListener){
        userRemoteDataSource.getProfile(token,dataSourceListener);

    }

//    public void saveUserProfile(DataSourceListener< RemoteUser> dataSourceListener){
//        userLocaleDataSourceImpl.
//    }

}
