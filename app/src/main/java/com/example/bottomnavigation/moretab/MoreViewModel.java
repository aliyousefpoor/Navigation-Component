package com.example.bottomnavigation.moretab;

import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.datasource.local.database.IsLoginListener;
import com.example.bottomnavigation.data.datasource.local.database.UserDatabase;

public class MoreViewModel extends ViewModel {
    private static final String TAG = "MoreViewModel";
    private UserLocaleDataSourceImpl userLocaleDataSource;


    public MoreViewModel(UserLocaleDataSourceImpl userLocaleDataSource ) {
        this.userLocaleDataSource = userLocaleDataSource;
    }

    private SingleLiveEvent<Boolean> _isLogin = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> isLogin = _isLogin;

    public void isLogin() {
        userLocaleDataSource.isLogin( new IsLoginListener() {
            @Override
            public void isLogin(Boolean isLogin) {
                if (isLogin) {
                    _isLogin.postValue(true);
                } else {
                    _isLogin.postValue(false);
                }
            }

        });
    }
}
