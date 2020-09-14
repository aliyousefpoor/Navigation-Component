package com.example.StreamApp.data.datasource.local.database;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.example.StreamApp.data.datasource.local.model.UserEntity;
import com.example.StreamApp.data.model.LoginStepTwoResponse;



public class LoginAsyncTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "MyAsyncTask";
    @SuppressLint("StaticFieldLeak")
    private UserDao userDao;
    private LoginStepTwoResponse loginStepTwoResponse;

    public LoginAsyncTask(LoginStepTwoResponse loginStepTwoResponse, UserDao userDao) {
        this.loginStepTwoResponse = loginStepTwoResponse;
        this.userDao = userDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        final UserEntity user = new UserEntity();

        user.setUserId(loginStepTwoResponse.getUserId());
        user.setToken(loginStepTwoResponse.getToken());
        userDao.insertUser(user);
        Log.d(TAG, "onResponse: " + user.getUserId() + "\n" + user.getToken());

        return null;
    }
}