package com.example.bottomnavigation.data.local.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.local.database.UserDatabase;
import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.User;

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
//        for (User user:userList){
            int id = userList.getUserId();
            String token = userList.getToken();
            String name = userList.getName();
            String date = userList.getDate();
            String gender = userList.getGender();



            info.append("\n\n").append("Id :").append(id).append("\n").append("Token : ")
                    .append(token).append("\n").append("Name :").append(name).append("\n")
                    .append("Date :").append(date).append("\n").append("Gender :").append(gender);
            Log.d(TAG, "doInBackground: " + info);

        return null;
    }


}
