package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.data.model.ProfileUpdate;
import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.model.UpdateResponseBody;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface UserRemoteDataSource {
    void updateProfile(ProfileUpdate profileUpdate, DataSourceListener<UpdateResponseBody> dataSourceListener);
    void getProfile(String token, DataSourceListener<RemoteUser> dataSourceListener);
    void updateImage(String token ,File file, DataSourceListener<UpdateResponseBody> dataSourceListener);
}
