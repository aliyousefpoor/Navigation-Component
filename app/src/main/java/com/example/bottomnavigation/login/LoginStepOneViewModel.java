package com.example.bottomnavigation.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.LoginStepOneResponse;
import com.example.bottomnavigation.data.model.LoginStepOne;
import com.example.bottomnavigation.data.datasource.remote.LoginStepOneRemoteDataSource;

public class LoginStepOneViewModel extends ViewModel {
    private static final String TAG = "UserViewModel";

    private LoginStepOneRemoteDataSource loginStepOneRemoteDataSource;

    public LoginStepOneViewModel(LoginStepOneRemoteDataSource loginStepOneRemoteDataSource){
        this.loginStepOneRemoteDataSource = loginStepOneRemoteDataSource;
    }



    MutableLiveData<LoginStepOneResponse> _loginStepOneLiveData = new MutableLiveData<>();
    LiveData<LoginStepOneResponse> loginStepOneLiveData = _loginStepOneLiveData;



    public void loginStepOne(LoginStepOne loginStepOne) {


        loginStepOneRemoteDataSource.loginStepOne(loginStepOne, new DataSourceListener<LoginStepOneResponse>() {
            @Override
            public void onResponse(LoginStepOneResponse response) {
                _loginStepOneLiveData.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }
}
