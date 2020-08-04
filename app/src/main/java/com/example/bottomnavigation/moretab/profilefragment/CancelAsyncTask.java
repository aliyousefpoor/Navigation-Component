package com.example.bottomnavigation.moretab.profilefragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.database.UserDataBase;
import com.example.bottomnavigation.data.model.User;

import java.util.List;

public class CancelAsyncTask extends AsyncTask<User,Void,User> {
    private static final String TAG = "CancelAsyncTask";
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public CancelAsyncTask(Context context){
        this.context=context;
    }
    @Override
    protected User doInBackground(User... users) {
        UserDataBase dataBase = UserDataBase.getInstance(context);
        User userList = dataBase.userDao().getUser();
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
