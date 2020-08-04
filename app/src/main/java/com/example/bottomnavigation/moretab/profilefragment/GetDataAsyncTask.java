package com.example.bottomnavigation.moretab.profilefragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.database.UserDataBase;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.moretab.UserInformationListener;

import java.util.List;

public class GetDataAsyncTask extends AsyncTask<User, Void, User> {
    private static final String TAG = "GetDataAsyncTask";
    private UserInformationListener userInformationListener;

    @SuppressLint("StaticFieldLeak")
    Context context;

    public GetDataAsyncTask(Context context, UserInformationListener userInformationListener) {
        this.userInformationListener = userInformationListener;
        this.context = context;
    }

    @Override
    protected User doInBackground(User... users) {
        UserDataBase dataBase = UserDataBase.getInstance(context);
        User userList = dataBase.userDao().getUser();
        StringBuilder info = new StringBuilder(" ");
        if (userList !=null) {
//            for (User user : userList) {
                int id = userList.getUserId();
                String token = userList.getToken();
                String name = userList.getName();
                String date = userList.getDate();
                String gender = userList.getGender();
                userInformationListener.onUserInformation(userList);


                info.append("\n\n").append("Id :").append(id).append("\n").append("Token : ")
                        .append(token).append("\n").append("Name :").append(name).append("\n")
                        .append("Date :").append(date).append("\n").append("Gender :").append(gender);
                Log.d(TAG, "doInBackground: " + info);
            }

        else {
            userInformationListener.onUserInformation(null);
        }

        return null;
    }

}
