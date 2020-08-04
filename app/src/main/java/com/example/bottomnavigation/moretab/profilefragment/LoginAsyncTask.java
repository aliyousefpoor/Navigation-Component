package com.example.bottomnavigation.moretab.profilefragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.database.UserDataBase;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.model.VerificationResponseBody;



public class LoginAsyncTask extends AsyncTask<User, Void, User>{
    private static final String TAG = "MyAsyncTask";
    @SuppressLint("StaticFieldLeak")
    Context context;
    VerificationResponseBody verificationResponseBody;

    public LoginAsyncTask(VerificationResponseBody verificationResponseBody, Context context) {
        this.verificationResponseBody=verificationResponseBody;
        this.context =context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected User doInBackground(User... users) {
        final User user = new User();
        UserDataBase dataBase = UserDataBase.getInstance(context);

        user.setUserId(verificationResponseBody.getUserId());
        user.setToken(verificationResponseBody.getToken());
        dataBase.userDao().insertUser(user);
        Log.d(TAG, "onResponse: " + user.getUserId() +"\n"+user.getToken());

        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
    }
}