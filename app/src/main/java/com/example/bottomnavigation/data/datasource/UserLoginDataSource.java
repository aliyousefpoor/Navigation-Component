package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.data.model.LoginResponseBody;
import com.example.bottomnavigation.data.model.LoginStepOne;

public interface UserLoginDataSource {

    void loginStepOne(LoginStepOne loginStepOne, DataSourceListener<LoginResponseBody> dataSourceListener);
}
