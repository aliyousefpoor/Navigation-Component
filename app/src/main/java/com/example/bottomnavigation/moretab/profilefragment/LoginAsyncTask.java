package com.example.bottomnavigation.moretab.profilefragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.local.database.UserDatabase;
import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.model.VerificationResponseBody;



public class LoginAsyncTask extends AsyncTask<UserEntity, Void, UserEntity> {
    private static final String TAG = "MyAsyncTask";
    @SuppressLint("StaticFieldLeak")
    Context context;
    VerificationResponseBody verificationResponseBody;

    public LoginAsyncTask(VerificationResponseBody verificationResponseBody, Context context) {
        this.verificationResponseBody = verificationResponseBody;
        this.context = context;
    }

    @Override
    protected UserEntity doInBackground(UserEntity... users) {
        final UserEntity user = new UserEntity();
        UserDatabase dataBase = UserDatabase.getInstance(context);

        user.setUserId(verificationResponseBody.getUserId());
        user.setToken(verificationResponseBody.getToken());
        dataBase.userDao().insertUser(user);
        Log.d(TAG, "onResponse: " + user.getUserId() + "\n" + user.getToken());

        return null;
    }
}