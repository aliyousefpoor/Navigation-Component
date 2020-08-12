package com.example.bottomnavigation.login.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.remote.LoginStepOneRemoteDataSource;
import com.example.bottomnavigation.data.remote.UserRemoteDataSourceImpl;
import com.example.bottomnavigation.data.remote.LoginStepTwoRemoteDataSource;
import com.example.bottomnavigation.data.repository.UserRepository;
import com.example.bottomnavigation.login.LoginStepOneViewModelFactory;
import com.example.bottomnavigation.login.LoginStepTwoViewModelFactory;

public class LoginModule {

    public static LoginStepOneRemoteDataSource provideLoginRemoteDataSource(ApiService apiService) {
        return new LoginStepOneRemoteDataSource(apiService);
    }

    public static LoginStepOneViewModelFactory provideLoginViewModelFactory(LoginStepOneRemoteDataSource loginStepOneRemoteDataSource) {
        return new LoginStepOneViewModelFactory(loginStepOneRemoteDataSource);
    }

    public static LoginStepTwoViewModelFactory provideVerificationViewModelFactory(LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource) {
        return new LoginStepTwoViewModelFactory(loginStepTwoRemoteDataSource);
    }

    public static LoginStepTwoRemoteDataSource provideVerificationRemoteDataSource(ApiService apiService) {
        return new LoginStepTwoRemoteDataSource(apiService);
    }

    public static UserLocaleDataSourceImpl provideUserLocaleDataSource() {
        return new UserLocaleDataSourceImpl();
    }


    public static UserRepository provideIsLoginRepository(UserLocaleDataSourceImpl userLocaleDataSourceImpl, UserRemoteDataSourceImpl userRemoteDataSource) {
        return new UserRepository(userLocaleDataSourceImpl, userRemoteDataSource);
    }

    public static UserRemoteDataSourceImpl provideUserRemoteDataSource(ApiService apiService) {
        return new UserRemoteDataSourceImpl(apiService);
    }

}
