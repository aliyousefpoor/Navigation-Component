package com.example.bottomnavigation.data.datasource.local.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.example.bottomnavigation.data.datasource.local.model.UserEntity;
import com.example.bottomnavigation.data.model.User;


public class UpdateUserAsyncTask extends AsyncTask<UserEntity, Void, UserEntity> {
    private static final String TAG = "UpdateAsyncTask";
    private User user;
    @SuppressLint("StaticFieldLeak")
    private Context context;


    public UpdateUserAsyncTask(User user, Context context) {
        this.user = user;
        this.context = context;
    }

    @Override
    protected UserEntity doInBackground(UserEntity... users) {
        //Todo injectiob
        UserDatabase dataBase = UserDatabase.getInstance(context);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        userEntity.setToken(user.getToken());
        userEntity.setName(user.getName());
        userEntity.setDate(user.getDate());
        userEntity.setGender(user.getGender());
        userEntity.setAvatar(user.getAvatar());
        dataBase.userDao().updateUser(userEntity);
//        dataBase.userDao().updateProfile(user.getName(),user.getToken(),user.getDate(),user.getGender());

        return null;
    }

}
