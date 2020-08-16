package com.example.bottomnavigation.data.datasource.local.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.datasource.local.model.UserEntity;

public class CancelAsyncTask extends AsyncTask<UserEntity,Void,UserEntity> {
    private static final String TAG = "CancelAsyncTask";
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public CancelAsyncTask(Context context){
        this.context=context;
    }
    @Override
    protected UserEntity doInBackground(UserEntity... users) {
        UserDatabase dataBase = UserDatabase.getInstance(context);
        UserEntity userList = dataBase.userDao().getUser();
        StringBuilder info = new StringBuilder(" ");

            int id = userList.getUserId();
            String token = userList.getToken();
            String name = userList.getName();
            String date = userList.getDate();
            String gender = userList.getGender();
            String avatar = userList.getAvatar();



            info.append("\n\n").append("Id :").append(id).append("\n").append("Token : ")
                    .append(token).append("\n").append("Name :").append(name).append("\n")
                    .append("Date :").append(date).append("\n").append("Gender :").append(gender).append("\n")
                    .append("Avatar :").append(avatar);
            Log.d(TAG, "doInBackground: " + info);

        return null;
    }


}
