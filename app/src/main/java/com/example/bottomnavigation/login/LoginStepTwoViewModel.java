package com.example.bottomnavigation.login;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;

import com.example.bottomnavigation.data.model.LoginStepTwo;
import com.example.bottomnavigation.data.model.LoginStepTwoResponseBody;
import com.example.bottomnavigation.data.remote.LoginStepTwoRemoteDataSource;

public class LoginStepTwoViewModel extends ViewModel {
    private static final String TAG = "UserVerificationViewMod";
    private LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource;


    public LoginStepTwoViewModel(LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource) {
        this.loginStepTwoRemoteDataSource = loginStepTwoRemoteDataSource;
    }

    private MutableLiveData<LoginStepTwoResponseBody> _LoginStepTwoLiveData = new MutableLiveData<>();
    public LiveData<LoginStepTwoResponseBody> loginStepTwoLiveData = _LoginStepTwoLiveData;

    public void loginStepTwo(LoginStepTwo loginStepTwo) {
        loginStepTwoRemoteDataSource.loginStepTwo(loginStepTwo, new DataSourceListener<LoginStepTwoResponseBody>() {

            @Override

            public void onResponse(LoginStepTwoResponseBody response) {
                _LoginStepTwoLiveData.setValue(response);

                Log.d(TAG, "onResponse:Verification ");

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: " + throwable);
            }

        });

    }

public void userLogin(LoginStepTwoResponseBody loginStepTwoResponseBody, Context context){
        loginStepTwoRemoteDataSource.userLogin(loginStepTwoResponseBody,context);
}
}
