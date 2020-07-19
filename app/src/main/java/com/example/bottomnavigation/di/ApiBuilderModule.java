package com.example.bottomnavigation.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.utils.ApiBuilder;

public class ApiBuilderModule {

    public static ApiService provideApiService (){
        return ApiBuilder.create(ApiService.class);
    }
}
