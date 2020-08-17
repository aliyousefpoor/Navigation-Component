package com.example.bottomnavigation.data.datasource.local.database.di;

import android.content.Context;

import com.example.bottomnavigation.data.datasource.local.database.UserDatabase;

public class DatabaseModule {
    public static UserDatabase provideUserDatabase(Context context){
        return UserDatabase.getInstance(context);
    }

}
