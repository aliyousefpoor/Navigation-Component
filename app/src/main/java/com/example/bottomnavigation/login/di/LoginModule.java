package com.example.bottomnavigation.login.di;

import android.content.Context;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.CustomApp;
import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.datasource.local.database.UserDao;
import com.example.bottomnavigation.data.datasource.local.database.UserDatabase;
import com.example.bottomnavigation.data.datasource.remote.LoginStepOneRemoteDataSource;
import com.example.bottomnavigation.data.datasource.remote.UserRemoteDataSourceImpl;
import com.example.bottomnavigation.data.datasource.remote.LoginStepTwoRemoteDataSource;
import com.example.bottomnavigation.data.repository.LoginRepository;
import com.example.bottomnavigation.data.repository.ProfileRepository;
import com.example.bottomnavigation.login.LoginSharedViewModelFactory;

public class LoginModule {

    public static LoginStepOneRemoteDataSource provideLoginStepOneRemoteDataSource(ApiService apiService) {
        return new LoginStepOneRemoteDataSource(apiService);
    }

    public static LoginSharedViewModelFactory provideShareViewModelFactory(LoginRepository loginRepository) {
        return new LoginSharedViewModelFactory(loginRepository);
    }

    public static LoginStepTwoRemoteDataSource provideLoginStepTwoRemoteDataSource(ApiService apiService) {
        return new LoginStepTwoRemoteDataSource(apiService);
    }

    public static UserLocaleDataSourceImpl provideUserLocaleDataSource(UserDao userDao) {
        return new UserLocaleDataSourceImpl(userDao);
    }


    public static ProfileRepository provideProfileRepository(UserLocaleDataSourceImpl userLocaleDataSourceImpl
            , UserRemoteDataSourceImpl userRemoteDataSource) {
        return new ProfileRepository(userLocaleDataSourceImpl, userRemoteDataSource);
    }

    public static LoginRepository provideLoginRepository(LoginStepOneRemoteDataSource loginStepOneRemoteDataSource
            , LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource, UserLocaleDataSourceImpl userLocaleDataSource){
     return new LoginRepository(loginStepOneRemoteDataSource,loginStepTwoRemoteDataSource,userLocaleDataSource);
    }

    public static UserRemoteDataSourceImpl provideUserRemoteDataSource(ApiService apiService) {
        return new UserRemoteDataSourceImpl(apiService);
    }

    public static UserDatabase provideUserDatabase() {
        Context context = CustomApp.getContext();
        return UserDatabase.getInstance(context);
    }

//    public void provideLoginRepository(ApiService apiService, UserDao userDao) {
//        provideLoginStepOneRemoteDataSource(apiService);
//        provideLoginStepTwoRemoteDataSource(apiService);
//        provideUserLocaleDataSource(userDao);
//    }

}
