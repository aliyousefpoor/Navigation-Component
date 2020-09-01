package com.example.bottomnavigation.data.datasource.local;


import android.util.Log;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.local.database.IsLoginListener;
import com.example.bottomnavigation.data.datasource.local.database.LoginAsyncTask;
import com.example.bottomnavigation.data.datasource.local.database.UserDao;
import com.example.bottomnavigation.data.model.LoginStepTwoResponse;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.datasource.local.database.GetUserDataAsyncTask;
import com.example.bottomnavigation.data.datasource.local.database.UpdateUserAsyncTask;

public class UserLocaleDataSourceImpl {
    private static final String TAG = "UserLocaleDataSourceImp";
    private UserDao userDao;

    public UserLocaleDataSourceImpl(UserDao userDao) {
        this. userDao= userDao;
    }

    public void saveUser(User user) {
        UpdateUserAsyncTask updateUserAsyncTask = new UpdateUserAsyncTask(user, userDao);
        updateUserAsyncTask.execute();
    }

    public void getUser(final DataSourceListener<User> dataSourceListener) {
        GetUserDataAsyncTask getUserDataAsyncTask = new GetUserDataAsyncTask(userDao, dataSourceListener);
        getUserDataAsyncTask.execute();
    }

    public void loginUser(LoginStepTwoResponse loginStepTwoResponse) {
        LoginAsyncTask loginAsyncTask = new LoginAsyncTask(loginStepTwoResponse, userDao);
        loginAsyncTask.execute();
    }

    public void isLogin(final IsLoginListener isLoginListener) {
        getUser(new DataSourceListener<User>() {
            @Override
            public void onResponse(User response) {
                isLoginListener.isLogin(true);

                Log.d(TAG, "onResponse: " + response.getToken());
            }

            @Override
            public void onFailure(Throwable throwable) {
                isLoginListener.isLogin(false);
            }
        });
    }

    public String getTokenBlocking() {
        if (userDao.getUser()!=null) {
            return userDao.getUser().getToken();
        }
        else {
            return null;
        }
    }

}
