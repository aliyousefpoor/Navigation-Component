package com.example.bottomnavigation;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.bottomnavigation.di.AppModule;


public class CustomApp extends Application {
    private static final String TAG = "CustomApp";
    public AppModule appModule = new AppModule();
    private static CustomApp instance;
    private static Context context;

    public static CustomApp getInstance() {
        return instance;
    }

    public AppModule getAppModule() {
        return appModule;
    }

    public static Context getContext(){
        return CustomApp.context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CustomApp.context = getApplicationContext();
        Log.d(TAG, "onCreate() called");

    }

}
