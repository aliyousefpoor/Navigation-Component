package com.example.bottomnavigation.moretab.profilefragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.local.database.UserDatabase;
import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.moretab.UserInformationListener;

public class GetDataAsyncTask extends AsyncTask<UserEntity, Void, UserEntity> {
    private static final String TAG = "GetDataAsyncTask";
    private UserInformationListener userInformationListener;

    @SuppressLint("StaticFieldLeak")
    Context context;

    public GetDataAsyncTask(Context context, UserInformationListener userInformationListener) {
        this.userInformationListener = userInformationListener;
        this.context = context;
    }

    @Override
    protected UserEntity doInBackground(UserEntity... users) {
        UserDatabase dataBase = UserDatabase.getInstance(context);
        UserEntity userEntity = dataBase.userDao().getUser();
        StringBuilder info = new StringBuilder(" ");
        if (userEntity !=null) {

                int id = userEntity.getUserId();
                String token = userEntity.getToken();
                String name = userEntity.getName();
                String date = userEntity.getDate();
                String gender = userEntity.getGender();
                userInformationListener.onUserInformation(userEntity);


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
