package com.example.bottomnavigation.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.model.UpdateResponseBody;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.remote.UserRemoteDataSourceImpl;
import com.example.bottomnavigation.data.local.database.UserInformationListener;

import java.io.File;

public class UserRepository {
    private static final String TAG = "IsLoginRepository";
    private UserLocaleDataSourceImpl userLocaleDataSourceImpl;
    private UserRemoteDataSourceImpl userRemoteDataSource;

    public UserRepository(UserLocaleDataSourceImpl userLocaleDataSourceImpl, UserRemoteDataSourceImpl userRemoteDataSource) {
        this.userLocaleDataSourceImpl = userLocaleDataSourceImpl;
        this.userRemoteDataSource = userRemoteDataSource;
    }

    public void saveUser(User user, Context context) {
        userLocaleDataSourceImpl.saveUser(user, context);
    }

    public void getUser(Context context, UserInformationListener userInformationListener) {
        userLocaleDataSourceImpl.getUser(context, userInformationListener);
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

    public void getProfile(final String token, final Context context, final DataSourceListener<RemoteUser> dataSourceListener) {
        userRemoteDataSource.getProfile(token, new DataSourceListener<RemoteUser>() {
            @Override
            public void onResponse(RemoteUser response) {
                User user = new User();
                user.setName(response.getNickName());
                user.setToken(token);
                user.setDate(response.getBirthdayDate());
                user.setGender(response.getGender());
                user.setUserId(response.getId());
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

}
