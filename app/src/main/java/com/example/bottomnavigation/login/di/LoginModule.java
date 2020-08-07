package com.example.bottomnavigation.login.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.remote.LoginRemoteDataSource;
import com.example.bottomnavigation.data.remote.UserRemoteDataSource;
import com.example.bottomnavigation.data.remote.VerificationRemoteDataSource;
import com.example.bottomnavigation.data.repository.IsLoginRepository;
import com.example.bottomnavigation.data.repository.LoginRepository;
import com.example.bottomnavigation.login.LoginViewModelFactory;
import com.example.bottomnavigation.login.VerificationViewModelFactory;

public class LoginModule {
    public  static LoginViewModelFactory provideLoginViewModelFactory(LoginRepository loginRepository){
        return new LoginViewModelFactory(loginRepository);
    }
    public static LoginRepository provideLoginSource(LoginRemoteDataSource loginRemoteDataSource) {
        return new LoginRepository(loginRemoteDataSource);
    }

    public static LoginRemoteDataSource provideLoginRemoteDataSource(ApiService apiService) {
        return new LoginRemoteDataSource(apiService);
    }

    public static VerificationViewModelFactory provideVerificationViewModelFactory(LoginRepository loginRepository){
        return new VerificationViewModelFactory(loginRepository);
    }

    public static LoginRepository provideVerificationSource(VerificationRemoteDataSource verificationRemoteDataSource) {
        return new LoginRepository(verificationRemoteDataSource);
    }

    public static VerificationRemoteDataSource provideVerificationRemoteDataSource(ApiService apiService) {
        return new VerificationRemoteDataSource(apiService);
    }

    public static UserLocaleDataSourceImpl provideUserLocaleDataSource(){
        return new UserLocaleDataSourceImpl();
    }


    public static IsLoginRepository provideIsLoginRepository(UserLocaleDataSourceImpl userLocaleDataSourceImpl) {
        return new IsLoginRepository(userLocaleDataSourceImpl);
    }

    public static UserRemoteDataSource provideUserRemoteDataSource(ApiService apiService){
        return new UserRemoteDataSource(apiService);
    }

    public static  IsLoginRepository provideIsLoginRepository(UserRemoteDataSource userRemoteDataSource){
        return new IsLoginRepository(userRemoteDataSource);
    }
}
