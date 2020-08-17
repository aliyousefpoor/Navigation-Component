package com.example.bottomnavigation.data.datasource.local.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.example.bottomnavigation.data.datasource.local.model.UserEntity;
import com.example.bottomnavigation.data.model.User;


public class UpdateUserAsyncTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "UpdateAsyncTask";
    private User user;
    @SuppressLint("StaticFieldLeak")
    private UserDatabase database;


    public UpdateUserAsyncTask(User user,UserDatabase database) {
        this.user = user;
        this.database = database;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //Todo injectiob
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setToken(user.getToken());
        userEntity.setName(user.getName());
        userEntity.setDate(user.getDate());
        userEntity.setGender(user.getGender());
        userEntity.setAvatar(user.getAvatar());
        database.userDao().updateUser(userEntity);
//        dataBase.userDao().updateProfile(user.getName(),user.getToken(),user.getDate(),user.getGender());

        return null;
    }

}
