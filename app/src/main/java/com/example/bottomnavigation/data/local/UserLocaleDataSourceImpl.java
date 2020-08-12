package com.example.bottomnavigation.data.local;

import android.content.Context;


import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.local.database.UserInformationListener;
import com.example.bottomnavigation.data.local.database.GetUserDataAsyncTask;
import com.example.bottomnavigation.data.local.database.UpdateUserAsyncTask;

public class UserLocaleDataSourceImpl {



    public void saveUser(User user, Context context) {
        UpdateUserAsyncTask updateUserAsyncTask = new UpdateUserAsyncTask(user, context);
        updateUserAsyncTask.execute();
    }

    public void getUser(Context context,UserInformationListener userInformationListener) {
        GetUserDataAsyncTask getUserDataAsyncTask = new GetUserDataAsyncTask(context,userInformationListener);
        getUserDataAsyncTask.execute();
    }

}
