package com.example.bottomnavigation.data.datasource.local.database;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.datasource.local.model.UserEntity;
import com.example.bottomnavigation.data.model.LoginStepTwoResponseBody;



public class LoginAsyncTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "MyAsyncTask";
    @SuppressLint("StaticFieldLeak")
    private UserDao userDao;
    private LoginStepTwoResponseBody loginStepTwoResponseBody;

    public LoginAsyncTask(LoginStepTwoResponseBody loginStepTwoResponseBody, UserDao userDao) {
        this.loginStepTwoResponseBody = loginStepTwoResponseBody;
        this.userDao = userDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        final UserEntity user = new UserEntity();

        user.setUserId(loginStepTwoResponseBody.getUserId());
        user.setToken(loginStepTwoResponseBody.getToken());
        userDao.insertUser(user);
        Log.d(TAG, "onResponse: " + user.getUserId() + "\n" + user.getToken());

        return null;
    }
}