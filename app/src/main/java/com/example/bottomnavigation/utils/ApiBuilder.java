package com.example.bottomnavigation.utils;

import android.util.Log;

import retrofit2.Retrofit;

public class ApiBuilder {
    public Retrofit retrofit;

    public ApiBuilder(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    private static final String TAG = "ApiBuilder";

    public <T> T create(final Class<T> serviceInterface) {
        Log.d(TAG, "create: ");
        return retrofit.create(serviceInterface);
    }

}
