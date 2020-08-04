package com.example.bottomnavigation.data.local;

import android.content.Context;


import com.example.bottomnavigation.data.datasource.LocalUserDataSource;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.moretab.UserInformationListener;
import com.example.bottomnavigation.moretab.profilefragment.GetDataAsyncTask;
import com.example.bottomnavigation.moretab.profilefragment.UpdateAsyncTask;

public class UserLocalDataSource implements LocalUserDataSource {

    //rename
    @Override
    public void saveInformation(User user, Context context) {
        UpdateAsyncTask updateAsyncTask = new UpdateAsyncTask(user, context);
        updateAsyncTask.execute();
    }

    @Override
    public void getUserInformation(Context context , UserInformationListener userInformationListener) {
        GetDataAsyncTask getDataAsyncTask = new GetDataAsyncTask(context,userInformationListener);
        getDataAsyncTask.execute();
    }
}
