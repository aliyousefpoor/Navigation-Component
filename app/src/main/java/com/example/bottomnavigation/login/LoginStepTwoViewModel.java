package com.example.bottomnavigation.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;

import com.example.bottomnavigation.data.datasource.local.database.UserDao;
import com.example.bottomnavigation.data.model.LoginStepTwo;
import com.example.bottomnavigation.data.model.LoginStepTwoResponse;
import com.example.bottomnavigation.data.datasource.remote.LoginStepTwoRemoteDataSource;

public class LoginStepTwoViewModel extends ViewModel {
    private static final String TAG = "UserVerificationViewMod";
    private LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource;


    public LoginStepTwoViewModel(LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource) {
        this.loginStepTwoRemoteDataSource = loginStepTwoRemoteDataSource;
    }

    private MutableLiveData<LoginStepTwoResponse> _LoginStepTwoLiveData = new MutableLiveData<>();
    public LiveData<LoginStepTwoResponse> loginStepTwoLiveData = _LoginStepTwoLiveData;

    public void loginStepTwo(LoginStepTwo loginStepTwo) {
        loginStepTwoRemoteDataSource.loginStepTwo(loginStepTwo, new DataSourceListener<LoginStepTwoResponse>() {

            @Override

            public void onResponse(LoginStepTwoResponse response) {
                _LoginStepTwoLiveData.setValue(response);

                Log.d(TAG, "onResponse:Verification ");

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: " + throwable);
            }

        });

    }

public void userLogin(LoginStepTwoResponse loginStepTwoResponse, UserDao userDao){
        loginStepTwoRemoteDataSource.userLogin(loginStepTwoResponse,userDao);
}
}
