package com.example.bottomnavigation.data.local.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.example.bottomnavigation.data.local.database.UserDatabase;
import com.example.bottomnavigation.data.local.database.di.DatabaseModule;
import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.User;


public class UpdateAsyncTask extends AsyncTask<UserEntity, Void, UserEntity> {
    private static final String TAG = "UpdateAsyncTask";
    private User user;
    @SuppressLint("StaticFieldLeak")
    private Context context;
    private UserEntity userEntity = DatabaseModule.provideUserEntity();


    public UpdateAsyncTask(User user , Context context) {
        this.user=user;
        this.context = context;
    }

    @Override
    protected UserEntity doInBackground(UserEntity... users) {
        UserDatabase dataBase = UserDatabase.getInstance(context);
        userEntity.setUserId(user.getUserId());
        userEntity.setToken(user.getToken());
        userEntity.setName(user.getName());
        userEntity.setDate(user.getDate());
        userEntity.setGender(user.getGender());
        dataBase.userDao().updateUser(userEntity);

        return null;
    }

}
