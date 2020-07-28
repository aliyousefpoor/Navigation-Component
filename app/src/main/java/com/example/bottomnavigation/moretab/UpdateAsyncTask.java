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
    int id;
    String token;
    String name;
    String date;
    String gender;
    @SuppressLint("StaticFieldLeak")
    private Context context;


    public UpdateAsyncTask( int id ,String token,String name, String date, String gender , Context context) {
        this.id =id;
        this.token =token;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.context = context;
    }

    @Override
    protected User doInBackground(User... users) {
        User user = new User();
        UserDataBase dataBase = UserDataBase.getInstance(context);
        user.setUserId(id);
        user.setToken(token);
        user.setName(name);
        user.setDate(date);
        user.setGender(gender);
        dataBase.userDao().updateUser(user);

        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
    }
}
