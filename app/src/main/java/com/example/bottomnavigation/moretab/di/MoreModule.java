package com.example.bottomnavigation.moretab.di;


import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.LoginSource;
import com.example.bottomnavigation.data.datasource.VerificationSource;


public class MoreModule {
    public static LoginSource provideUserSource(ApiService apiService) {
        return new LoginSource(apiService);
    }

    public static VerificationSource provideUserVerificationSource(ApiService apiService) {
        return new VerificationSource(apiService);
    }

}
