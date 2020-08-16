package com.example.bottomnavigation.data.local;

import android.content.Context;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.local.database.GetUserDataAsyncTask;
import com.example.bottomnavigation.data.local.database.UpdateUserAsyncTask;

public class UserLocaleDataSourceImpl {

    public void saveUser(User user, Context context) {
        UpdateUserAsyncTask updateUserAsyncTask = new UpdateUserAsyncTask(user, context);
        updateUserAsyncTask.execute();
    }

    public void getUser(Context context, DataSourceListener<User> dataSourceListener ) {
        GetUserDataAsyncTask getUserDataAsyncTask = new GetUserDataAsyncTask(context, dataSourceListener);
        getUserDataAsyncTask.execute();
    }

}
