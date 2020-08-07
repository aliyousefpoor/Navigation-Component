package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.data.model.ProfileUpdate;
import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.model.UpdateResponseBody;

public interface UserRemoteDataSource {
    void updateProfile(ProfileUpdate profileUpdate, DataSourceListener<UpdateResponseBody> dataSourceListener);
    void getProfile(String token, DataSourceListener<RemoteUser> dataSourceListener);
}
