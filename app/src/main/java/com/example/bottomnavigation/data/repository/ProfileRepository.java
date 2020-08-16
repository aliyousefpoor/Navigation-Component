package com.example.bottomnavigation.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.model.UpdateResponseBody;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.datasource.remote.UserRemoteDataSourceImpl;


import java.io.File;

public class ProfileRepository {
    private static final String TAG = "UserRepository";
    private UserLocaleDataSourceImpl userLocaleDataSourceImpl;
    private UserRemoteDataSourceImpl userRemoteDataSource;

    public ProfileRepository(UserLocaleDataSourceImpl userLocaleDataSourceImpl, UserRemoteDataSourceImpl userRemoteDataSource) {
        this.userLocaleDataSourceImpl = userLocaleDataSourceImpl;
        this.userRemoteDataSource = userRemoteDataSource;
    }


    public void getProfile(final Context context, final DataSourceListener<User> dataSourceListener) {
        userLocaleDataSourceImpl.getUser(context, new DataSourceListener<User>() {
            @Override
            public void onResponse(User response) {
                Log.d(TAG, "onResponse: " + response.getToken());
                dataSourceListener.onResponse(response);
                getProfile(response.getToken(), context, dataSourceListener);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
                dataSourceListener.onFailure(throwable);
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
                userLocaleDataSourceImpl.saveUser(user, context);
                dataSourceListener.onResponse(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                dataSourceListener.onFailure(throwable);
            }
        });
    }

    private void getProfile(final String token, final Context context, final DataSourceListener<User> dataSourceListener) {
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
                userLocaleDataSourceImpl.saveUser(user, context);
                dataSourceListener.onResponse(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                dataSourceListener.onFailure(throwable);
            }
        });
    }

    public void updateImage(String token, File file, DataSourceListener<UpdateResponseBody> dataSourceListener) {
        userRemoteDataSource.updateImage(token, file, dataSourceListener);
        Log.d(TAG, "updateImage: " + dataSourceListener);
    }
}
