package com.example.bottomnavigation.utils;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {

    private static final String TAG = "ApiBuilder";

    public static <T> T create(final Class<T> serviceInterface) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(AppConstants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Log.d(TAG, "create: ");
        return retrofit.create(serviceInterface);

    }
}
