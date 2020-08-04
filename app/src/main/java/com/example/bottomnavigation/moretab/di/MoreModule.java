package com.example.bottomnavigation.moretab.di;


import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.remote.LoginRemoteDataSource;
import com.example.bottomnavigation.data.local.UserLocalDataSource;
import com.example.bottomnavigation.data.remote.VerificationRemoteDataSource;
import com.example.bottomnavigation.data.repository.LoginRepository;


public class MoreModule {
    public static LoginRemoteDataSource provideLoginRemoteDataSource(ApiService apiService) {
        return new LoginRemoteDataSource(apiService);
    }

    public static VerificationRemoteDataSource provideVerificationRemoteDataSource(ApiService apiService) {
        return new VerificationRemoteDataSource(apiService);
    }

    public static UserLocalDataSource provideUserLocaleDataSource(){
        return new UserLocalDataSource();
    }

    public static LoginRepository provideLoginSource(LoginRemoteDataSource loginRemoteDataSource) {
        return new LoginRepository(loginRemoteDataSource);
    }

    public static LoginRepository provideVerificationSource(VerificationRemoteDataSource verificationRemoteDataSource) {
        return new LoginRepository(verificationRemoteDataSource);
    }

    public static LoginRepository provideUserLocaleDataSource(UserLocalDataSource userLocalDataSource) {
        return new LoginRepository(userLocalDataSource);
    }

}
