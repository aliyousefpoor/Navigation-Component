package com.example.StreamApp.data.datasource.local.database;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.example.StreamApp.data.datasource.local.model.UserEntity;

public class CancelAsyncTask extends AsyncTask<Void,Void,Void> {
    private static final String TAG = "CancelAsyncTask";
    @SuppressLint("StaticFieldLeak")
    private UserDatabase database;

    public CancelAsyncTask(UserDatabase database){
        this.database=database;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        UserEntity userList = database.userDao().getUser();
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
