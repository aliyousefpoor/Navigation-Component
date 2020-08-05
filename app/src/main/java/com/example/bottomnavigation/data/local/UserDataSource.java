package com.example.bottomnavigation.data.local;

import android.content.Context;


import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.moretab.UserInformationListener;
import com.example.bottomnavigation.data.local.database.GetDataAsyncTask;
import com.example.bottomnavigation.data.local.database.UpdateAsyncTask;

public class UserDataSource implements com.example.bottomnavigation.data.datasource.UserDataSource {


    @Override
    public void saveUser(User user, Context context) {
        UpdateAsyncTask updateAsyncTask = new UpdateAsyncTask(user, context);
        updateAsyncTask.execute();
    }

    @Override
    public void getUser(Context context , UserInformationListener userInformationListener) {
        GetDataAsyncTask getDataAsyncTask = new GetDataAsyncTask(context,userInformationListener);
        getDataAsyncTask.execute();
    }
}
