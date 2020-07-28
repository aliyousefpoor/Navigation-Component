package com.example.bottomnavigation.moretab.profilefragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.database.UserDataBase;
import com.example.bottomnavigation.data.model.User;

import java.util.List;

public class GetDataAsyncTask extends AsyncTask<User,Void, User> {
    private static final String TAG = "GetDataAsyncTask";

     @SuppressLint("StaticFieldLeak")
     Context context;

    public GetDataAsyncTask(Context context){
        this.context=context;
    }
    @Override
    protected User doInBackground(User... users) {
        UserDataBase dataBase = UserDataBase.getInstance(context);
        List<User> userList = dataBase.userDao().getAll();
        StringBuilder info = new StringBuilder(" ");
        for (User user:userList){
            int id = user.getUserId();
            String token = user.getToken();
            String name = user.getName();
            String date =user.getDate();
            String gender = user.getGender();

            info.append("\n\n").append("Id :").append(id).append("\n").append("Token : ")
                    .append(token).append("\n").append("Name :").append(name).append("\n")
                    .append("Date :").append(date).append("\n").append("Gender :").append(gender);
            Log.d(TAG, "doInBackground: " + info);
        }
        return null;
    }

}
