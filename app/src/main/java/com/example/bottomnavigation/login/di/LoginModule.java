package com.example.bottomnavigation.login.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.local.UserDataSource;
import com.example.bottomnavigation.data.remote.LoginRemoteDataSource;
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

    public static UserDataSource provideUserDataSource(){
        return new UserDataSource();
    }


    public static IsLoginRepository provideIsLoginRepository(UserDataSource userDataSource) {
        return new IsLoginRepository(userDataSource);
    }
}
