package com.example.bottomnavigation.data.local.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.LoginStepTwoResponseBody;



public class LoginAsyncTask extends AsyncTask<UserEntity, Void, UserEntity> {
    private static final String TAG = "MyAsyncTask";
    @SuppressLint("StaticFieldLeak")
    Context context;
    LoginStepTwoResponseBody loginStepTwoResponseBody;

    public LoginAsyncTask(LoginStepTwoResponseBody loginStepTwoResponseBody, Context context) {
        this.loginStepTwoResponseBody = loginStepTwoResponseBody;
        this.context = context;
    }

    @Override
    protected UserEntity doInBackground(UserEntity... users) {
        final UserEntity user = new UserEntity();
        UserDatabase dataBase = UserDatabase.getInstance(context);

        user.setUserId(loginStepTwoResponseBody.getUserId());
        user.setToken(loginStepTwoResponseBody.getToken());
        dataBase.userDao().insertUser(user);
        Log.d(TAG, "onResponse: " + user.getUserId() + "\n" + user.getToken());

        return null;
    }
}