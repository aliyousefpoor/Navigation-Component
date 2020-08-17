package com.example.bottomnavigation.data.datasource.local;


import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.local.database.IsLoginListener;
import com.example.bottomnavigation.data.datasource.local.database.UserDatabase;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.datasource.local.database.GetUserDataAsyncTask;
import com.example.bottomnavigation.data.datasource.local.database.UpdateUserAsyncTask;

public class UserLocaleDataSourceImpl {

    public void saveUser(User user, UserDatabase database) {
        UpdateUserAsyncTask updateUserAsyncTask = new UpdateUserAsyncTask(user, database);
        updateUserAsyncTask.execute();
    }

    public void getUser(UserDatabase database, DataSourceListener<User> dataSourceListener) {
        GetUserDataAsyncTask getUserDataAsyncTask = new GetUserDataAsyncTask(database, dataSourceListener);
        getUserDataAsyncTask.execute();
    }

    public void isLogin(UserDatabase database, final IsLoginListener isLoginListener) {
        getUser(database, new DataSourceListener<User>() {
            @Override
            public void onResponse(User response) {
                isLoginListener.isLogin(true);
            }

            @Override
            public void onFailure(Throwable throwable) {
                isLoginListener.isLogin(false);
            }
        });

    }
}
