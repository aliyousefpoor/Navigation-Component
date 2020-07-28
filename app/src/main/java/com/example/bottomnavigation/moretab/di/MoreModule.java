package com.example.bottomnavigation.moretab.di;


import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.LoginRemoteDataSource;
import com.example.bottomnavigation.data.datasource.UserLocaleDataSource;
import com.example.bottomnavigation.data.datasource.VerificationRemoteDataSource;
import com.example.bottomnavigation.data.repository.LoginRepository;


public class MoreModule {
    public static LoginRemoteDataSource provideLoginRemoteDataSource(ApiService apiService) {
        return new LoginRemoteDataSource(apiService);
    }

    public static VerificationRemoteDataSource provideVerificationRemoteDataSource(ApiService apiService) {
        return new VerificationRemoteDataSource(apiService);
    }

    public static UserLocaleDataSource provideUserLocaleDataSource(){
        return new UserLocaleDataSource();
    }

    public static LoginRepository provideLoginSource(LoginRemoteDataSource loginRemoteDataSource) {
        return new LoginRepository(loginRemoteDataSource);
    }

    public static LoginRepository provideVerificationSource(VerificationRemoteDataSource verificationRemoteDataSource) {
        return new LoginRepository(verificationRemoteDataSource);
    }

    public static LoginRepository provideUserLocaleDataSource(UserLocaleDataSource userLocaleDataSource) {
        return new LoginRepository(userLocaleDataSource);
    }

}
