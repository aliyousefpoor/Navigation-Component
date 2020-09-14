package com.example.StreamApp.data.datasource.local.database;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.StreamApp.data.datasource.DataSourceListener;
import com.example.StreamApp.data.datasource.local.model.UserEntity;
import com.example.StreamApp.data.model.User;

public class GetUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "GetDataAsyncTask";
    private DataSourceListener<User> dataSourceListener;

    @SuppressLint("StaticFieldLeak")
    private UserDao userDao ;

    public GetUserDataAsyncTask(UserDao userDao, DataSourceListener<User> dataSourceListener) {
        this.dataSourceListener = dataSourceListener;
        this.userDao = userDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        UserEntity userEntity = userDao.getUser();
        StringBuilder info = new StringBuilder(" ");
        if (userEntity != null) {
            User user = new User();

            user.setUserId(userEntity.getUserId());
            user.setToken(userEntity.getToken());
            user.setName(userEntity.getName());
            user.setDate(userEntity.getDate());
            user.setGender(userEntity.getGender());
            user.setAvatar(userEntity.getAvatar());

            dataSourceListener.onResponse(user);

        } else {
            dataSourceListener.onFailure(null);
        }

        return null;
    }

}
