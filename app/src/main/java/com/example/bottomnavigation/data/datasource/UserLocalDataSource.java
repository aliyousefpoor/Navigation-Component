package com.example.bottomnavigation.data.datasource;

import android.content.Context;


import com.example.bottomnavigation.moretab.UserInformationListener;
import com.example.bottomnavigation.moretab.profilefragment.GetDataAsyncTask;
import com.example.bottomnavigation.moretab.profilefragment.UpdateAsyncTask;

public class UserLocalDataSource implements LocalUserDataSource {

    @Override
    public void saveInformation(int userId, String token, String name, String date, String
            gender, Context context) {
        UpdateAsyncTask updateAsyncTask = new UpdateAsyncTask(userId, token, name, date, gender, context);
        updateAsyncTask.execute();
    }

    @Override
    public void getUserInformation(Context context , UserInformationListener userInformationListener) {
        GetDataAsyncTask getDataAsyncTask = new GetDataAsyncTask(context,userInformationListener);
        getDataAsyncTask.execute();
    }
}
