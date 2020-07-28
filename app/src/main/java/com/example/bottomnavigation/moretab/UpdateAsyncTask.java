package com.example.bottomnavigation.moretab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bottomnavigation.data.database.UserDataBase;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.model.VerificationResponseBody;

public class UpdateAsyncTask extends AsyncTask<User, Void, User> {
    private static final String TAG = "UpdateAsyncTask";

    private VerificationResponseBody verificationResponseBody;
    String name;
    String date;
    String gender;
    @SuppressLint("StaticFieldLeak")
    private Context context;


    public UpdateAsyncTask(VerificationResponseBody verificationResponseBody, Context context,
                           String name, String date, String gender) {
        this.verificationResponseBody = verificationResponseBody;
        this.context = context;
        this.name = name;
        this.date = date;
        this.gender = gender;
    }

    @Override
    protected User doInBackground(User... users) {
        User user = new User();
        UserDataBase dataBase = UserDataBase.getInstance(context);
        user.setUserId(verificationResponseBody.getUserId());
        user.setToken(verificationResponseBody.getToken());
        user.setName(name);
        user.setDate(date);
        user.setGender(gender);
        dataBase.userDao().updateUser(user);
        Log.d(TAG, "run: " + user.getUserId() + "   " + user.getToken()
                + "   " + user.getName() + "   " + user.getDate() + "   " + user.getGender());

        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
    }
}
