package com.example.bottomnavigation;

import android.app.Application;
import android.util.Log;

import com.example.bottomnavigation.di.AppModule;


public class CustomApp extends Application {
    private static final String TAG = "CustomApp";
    public  AppModule appModule= new AppModule() ;

    private static CustomApp instance;

    public static CustomApp getInstance() {
        return instance;
    }

    public  AppModule getAppModule(){
        return appModule;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Log.d(TAG, "onCreate() called");

    }

}
