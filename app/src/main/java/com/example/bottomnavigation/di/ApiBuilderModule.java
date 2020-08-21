package com.example.bottomnavigation.di;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.utils.ApiBuilder;

import retrofit2.Retrofit;

public class ApiBuilderModule {

    public static ApiService provideApiService (ApiBuilder builder){
        return  builder.create(ApiService.class);

    }

    public static ApiBuilder provideApiBuilder(Retrofit retrofit){
        return new ApiBuilder(retrofit);
    }


}
