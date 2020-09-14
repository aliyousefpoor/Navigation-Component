package com.example.StreamApp.di;

import com.example.StreamApp.ApiService;
import com.example.StreamApp.utils.ApiBuilder;

import retrofit2.Retrofit;

public class ApiBuilderModule {

    public static ApiService provideApiService (ApiBuilder builder){
        return  builder.create(ApiService.class);

    }

    public static ApiBuilder provideApiBuilder(Retrofit retrofit){
        return new ApiBuilder(retrofit);
    }


}
