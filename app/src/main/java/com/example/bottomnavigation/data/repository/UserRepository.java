package com.example.bottomnavigation.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.local.database.IsLoginListener;
import com.example.bottomnavigation.data.model.UpdateResponseBody;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.remote.UserRemoteDataSourceImpl;


import java.io.File;

public class UserRepository {
    private static final String TAG = "UserRepository";
    private UserLocaleDataSourceImpl userLocaleDataSourceImpl;
    private UserRemoteDataSourceImpl userRemoteDataSource;

    public UserRepository(UserLocaleDataSourceImpl userLocaleDataSourceImpl, UserRemoteDataSourceImpl userRemoteDataSource) {
        this.userLocaleDataSourceImpl = userLocaleDataSourceImpl;
        this.userRemoteDataSource = userRemoteDataSource;
    }

    public void saveUser(User user, Context context) {
        userLocaleDataSourceImpl.saveUser(user, context);
    }

    public void getUser(Context context, final DataSourceListener<User> dataSourceListener) {
        userLocaleDataSourceImpl.getUser(context, new DataSourceListener<User>() {
            @Override
            public void onResponse(User response) {
                Log.d(TAG, "onResponse: "+response.getToken());
                userRemoteDataSource.getProfile(response.getToken(),dataSourceListener);
                dataSourceListener.onResponse(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
            }
        });


    }

    public void updateProfile(final User user, final Context context, final DataSourceListener<UpdateResponseBody> dataSourceListener) {
        userRemoteDataSource.updateProfile(user, new DataSourceListener<UpdateResponseBody>() {
            @Override
            public void onResponse(UpdateResponseBody response) {

                user.setGender(response.getData().getGender());
                user.setDate(response.getData().getBirthdayDate());
                user.setName(response.getData().getNickName());
                user.setAvatar(response.getData().getAvatar());
                saveUser(user, context);
                dataSourceListener.onResponse(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                dataSourceListener.onFailure(throwable);
            }
        });
    }

    public void getProfile(final String token, final Context context, final DataSourceListener<User> dataSourceListener) {
        userRemoteDataSource.getProfile(token, new DataSourceListener<User>() {
            @Override
            public void onResponse(User response) {
                User user = new User();
                user.setName(response.getName());
                user.setToken(token);
                user.setDate(response.getDate());
                user.setGender(response.getGender());
                user.setUserId(response.getUserId());
                user.setAvatar(response.getAvatar());
                saveUser(user, context);
                dataSourceListener.onResponse(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                dataSourceListener.onFailure(throwable);
            }
        });
    }

    public void updateImage(String token ,File file, DataSourceListener<UpdateResponseBody> dataSourceListener){
        userRemoteDataSource.updateImage(token,file,dataSourceListener);
        Log.d(TAG, "updateImage: "+dataSourceListener);

    }

    public void isLogin(Context context, final IsLoginListener isLoginListener){
        userLocaleDataSourceImpl.getUser(context, new DataSourceListener<User>() {
            @Override
            public void onResponse(User response) {

                isLoginListener.isLogin(true);
            }

            @Override
            public void onFailure(Throwable throwable) {
                isLoginListener.isLogin(false);
            }
        });
    }

}
