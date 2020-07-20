package com.example.bottomnavigation.di;

import com.example.bottomnavigation.utils.AppConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppModule {

    private Retrofit retrofit = null;

    public Retrofit provideRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(AppConstants.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
