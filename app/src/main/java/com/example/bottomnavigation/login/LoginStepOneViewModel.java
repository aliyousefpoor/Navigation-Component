package com.example.bottomnavigation.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.LoginStepOneResponseBody;
import com.example.bottomnavigation.data.model.LoginStepOne;
import com.example.bottomnavigation.data.datasource.remote.LoginStepOneRemoteDataSource;

public class LoginStepOneViewModel extends ViewModel {
    private static final String TAG = "UserViewModel";

    private LoginStepOneRemoteDataSource loginStepOneRemoteDataSource;

    public LoginStepOneViewModel(LoginStepOneRemoteDataSource loginStepOneRemoteDataSource) {
        this.loginStepOneRemoteDataSource = loginStepOneRemoteDataSource;
    }


    private MutableLiveData<LoginStepOneResponseBody> _loginStepOneLiveData = new MutableLiveData<>();
    public LiveData<LoginStepOneResponseBody> loginStepOneLiveData = _loginStepOneLiveData;


    public void loginStepOne(LoginStepOne loginStepOne) {


        loginStepOneRemoteDataSource.loginStepOne(loginStepOne, new DataSourceListener<LoginStepOneResponseBody>() {
            @Override
            public void onResponse(LoginStepOneResponseBody response) {
                _loginStepOneLiveData.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
                _loginStepOneLiveData.setValue(null);
            }
        });

    }
}
