package com.example.bottomnavigation.login.di;

import android.content.Context;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.CustomApp;
import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.datasource.local.database.UserDatabase;
import com.example.bottomnavigation.data.datasource.remote.LoginStepOneRemoteDataSource;
import com.example.bottomnavigation.data.datasource.remote.UserRemoteDataSourceImpl;
import com.example.bottomnavigation.data.datasource.remote.LoginStepTwoRemoteDataSource;
import com.example.bottomnavigation.data.repository.ProfileRepository;
import com.example.bottomnavigation.login.LoginStepOneViewModelFactory;
import com.example.bottomnavigation.login.LoginStepTwoViewModelFactory;
import com.example.bottomnavigation.login.LoginSharedViewModelFactory;

public class LoginModule {

    public static LoginStepOneRemoteDataSource provideLoginStepOneRemoteDataSource(ApiService apiService) {
        return new LoginStepOneRemoteDataSource(apiService);
    }

    public static LoginStepOneViewModelFactory provideLoginStepOneViewModelFactory(LoginStepOneRemoteDataSource loginStepOneRemoteDataSource) {
        return new LoginStepOneViewModelFactory(loginStepOneRemoteDataSource);
    }

    public static LoginStepTwoViewModelFactory provideLoginStepTwoViewModelFactory(LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource) {
        return new LoginStepTwoViewModelFactory(loginStepTwoRemoteDataSource);
    }

    public static LoginSharedViewModelFactory provideShareViewModelFactory(LoginStepOneRemoteDataSource loginStepOneRemoteDataSource
    , LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource){
        return new LoginSharedViewModelFactory(loginStepOneRemoteDataSource, loginStepTwoRemoteDataSource);
    }
    public static LoginStepTwoRemoteDataSource provideLoginStepTwoRemoteDataSource(ApiService apiService) {
        return new LoginStepTwoRemoteDataSource(apiService);
    }

    public static UserLocaleDataSourceImpl provideUserLocaleDataSource() {
        return new UserLocaleDataSourceImpl();
    }


    public static ProfileRepository provideProfileRepository(UserLocaleDataSourceImpl userLocaleDataSourceImpl
            ,UserRemoteDataSourceImpl userRemoteDataSource,UserDatabase database) {
        return new ProfileRepository(userLocaleDataSourceImpl, userRemoteDataSource,database);
    }

    public static UserRemoteDataSourceImpl provideUserRemoteDataSource(ApiService apiService) {
        return new UserRemoteDataSourceImpl(apiService);
    }

   public static UserDatabase provideUserDatabase(){
        Context context =CustomApp.getContext();
        return UserDatabase.getInstance(context);
   }

}
