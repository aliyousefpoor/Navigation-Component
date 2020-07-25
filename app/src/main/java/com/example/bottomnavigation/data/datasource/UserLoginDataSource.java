package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.data.model.LoginBody;
import com.example.bottomnavigation.data.model.ResponseLoginBody;

public interface UserLoginDataSource {
    void postNumber(String number ,String androidId,String deviceModel,String deviceOs,DataSourceListener<ResponseLoginBody> dataSourceListener);
}
