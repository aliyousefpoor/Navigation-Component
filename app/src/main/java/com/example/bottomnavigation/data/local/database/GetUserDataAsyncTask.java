package com.example.bottomnavigation.data.local.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.User;

public class GetUserDataAsyncTask extends AsyncTask<UserEntity, Void, UserEntity> {
    private static final String TAG = "GetDataAsyncTask";
    private DataSourceListener<User> dataSourceListener;

    @SuppressLint("StaticFieldLeak")
    private Context context;

    public GetUserDataAsyncTask(Context context, DataSourceListener<User> dataSourceListener) {
        this.dataSourceListener = dataSourceListener;
        this.context = context;
    }

    @Override
    protected UserEntity doInBackground(UserEntity... users) {
        UserDatabase dataBase = UserDatabase.getInstance(context);
        UserEntity userEntity = dataBase.userDao().getUser();
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

//            info.append("\n\n").append("Id :").append(id).append("\n").append("Token : ")
//                    .append(token).append("\n").append("Name :").append(name).append("\n")
//                    .append("Date :").append(date).append("\n").append("Gender :").append(gender);
//            Log.d(TAG, "doInBackground: " + info);
        } else {
            dataSourceListener.onFailure(null);
        }

        return null;
    }

}
