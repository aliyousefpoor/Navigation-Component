package com.example.bottomnavigation.moretab.di;


import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.LoginRemoteDataSource;
import com.example.bottomnavigation.data.datasource.VerificationRemoteDataSource;
import com.example.bottomnavigation.data.repository.LoginRepository;


public class MoreModule {
    public static LoginRemoteDataSource provideUserSource(ApiService apiService) {
        return new LoginRemoteDataSource(apiService);
    }

    public static VerificationRemoteDataSource provideUserVerificationSource(ApiService apiService) {
        return new VerificationRemoteDataSource(apiService);
    }

    public static LoginRepository provideLoginSource(LoginRemoteDataSource loginRemoteDataSource){
        return new LoginRepository(loginRemoteDataSource);
    }

    public static LoginRepository provideVerificationSource(VerificationRemoteDataSource verificationRemoteDataSource){
        return new LoginRepository(verificationRemoteDataSource);
    }

}
