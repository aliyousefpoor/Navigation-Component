package com.example.bottomnavigation.moretab;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.datasource.local.database.IsLoginListener;

public class MoreViewModel extends ViewModel {
    private static final String TAG = "MoreViewModel";
    private UserLocaleDataSourceImpl userLocaleDataSource;


    public MoreViewModel(UserLocaleDataSourceImpl userLocaleDataSource ) {
        this.userLocaleDataSource = userLocaleDataSource;
    }

    private SingleLiveEvent<Boolean> _isLogin = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> isLogin = _isLogin;

    public void isLogin(Context context) {
        userLocaleDataSource.isLogin(context, new IsLoginListener() {
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
