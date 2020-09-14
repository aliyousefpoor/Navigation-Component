package com.example.StreamApp.login.di;

import android.content.Context;

import com.example.StreamApp.ApiService;
import com.example.StreamApp.CustomApp;
import com.example.StreamApp.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.StreamApp.data.datasource.local.database.UserDao;
import com.example.StreamApp.data.datasource.local.database.UserDatabase;
import com.example.StreamApp.data.datasource.remote.LoginStepOneRemoteDataSource;
import com.example.StreamApp.data.datasource.remote.UserRemoteDataSourceImpl;
import com.example.StreamApp.data.datasource.remote.LoginStepTwoRemoteDataSource;
import com.example.StreamApp.data.repository.LoginRepository;
import com.example.StreamApp.data.repository.ProfileRepository;
import com.example.StreamApp.login.LoginSharedViewModelFactory;

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

    public static LoginRepository provideLoginRepository(ApiService apiService,UserDao userDao) {

        return new LoginRepository(provideLoginStepOneRemoteDataSource(apiService), provideLoginStepTwoRemoteDataSource(apiService)
                ,provideUserLocaleDataSource(userDao));
    }

    public static UserRemoteDataSourceImpl provideUserRemoteDataSource(ApiService apiService) {
        return new UserRemoteDataSourceImpl(apiService);
    }

   public static UserDatabase provideUserDatabase() {
        Context context = CustomApp.getContext();
        return UserDatabase.getInstance(context);
   }

}
