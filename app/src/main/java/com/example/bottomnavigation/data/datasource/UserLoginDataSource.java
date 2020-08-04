package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.data.model.LoginResponseBody;

public interface UserLoginDataSource {
    void postNumber(String number ,String androidId,String deviceModel,String deviceOs,DataSourceListener<LoginResponseBody> dataSourceListener);
}
