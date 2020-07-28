package com.example.bottomnavigation.data.datasource;

import android.content.Context;


import com.example.bottomnavigation.moretab.UpdateAsyncTask;

public class UserLocaleDataSource implements LocaleUserDataSource {

    @Override
    public void saveInformation(int userId, String token, String name, String date, String
            gender, Context context) {
        UpdateAsyncTask updateAsyncTask = new UpdateAsyncTask(userId, token, name, date, gender, context);
        updateAsyncTask.execute();
    }
}
