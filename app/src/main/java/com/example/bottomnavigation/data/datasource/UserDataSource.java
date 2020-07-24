package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.data.model.User;

public interface UserDataSource {
    void postNumber(String number ,String androidId,String deviceModel,String deviceOs,DataSourceListener<User> dataSourceListener);
}
