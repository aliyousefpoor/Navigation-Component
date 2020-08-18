package com.example.bottomnavigation.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.local.database.UserDatabase;
import com.example.bottomnavigation.data.datasource.remote.LoginStepOneRemoteDataSource;
import com.example.bottomnavigation.data.datasource.remote.LoginStepTwoRemoteDataSource;
import com.example.bottomnavigation.data.model.LoginStepOne;
import com.example.bottomnavigation.data.model.LoginStepOneResponseBody;
import com.example.bottomnavigation.data.model.LoginStepTwo;
import com.example.bottomnavigation.data.model.LoginStepTwoResponseBody;

public class LoginSharedViewModel extends ViewModel {
    private static final String TAG = "LoginSharedViewModel";
    private LoginStepOneRemoteDataSource loginStepOneRemoteDataSource;
    private LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource;
     LoginStepOne loginStepOneBody  ;

    public LoginSharedViewModel(LoginStepOneRemoteDataSource loginStepOneRemoteDataSource
            , LoginStepTwoRemoteDataSource loginStepTwoRemoteDataSource) {
        this.loginStepOneRemoteDataSource = loginStepOneRemoteDataSource;
        this.loginStepTwoRemoteDataSource = loginStepTwoRemoteDataSource;
    }

    private MutableLiveData<LoginStepOneResponseBody> _loginStepOneLiveData = new MutableLiveData<>();
    public LiveData<LoginStepOneResponseBody> loginStepOneLiveData = _loginStepOneLiveData;

    private MutableLiveData<LoginStepTwoResponseBody> _loginStepTwoLiveData = new MutableLiveData<>();
    public LiveData<LoginStepTwoResponseBody> loginStepTwoLiveData = _loginStepTwoLiveData;

    public void loginStepOne(LoginStepOne loginStepOne) {
        loginStepOneBody =loginStepOne;
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

    public void loginStepTwo(LoginStepTwo loginStepTwo) {
        loginStepTwoRemoteDataSource.loginStepTwo(loginStepTwo, new DataSourceListener<LoginStepTwoResponseBody>() {
            @Override
            public void onResponse(LoginStepTwoResponseBody response) {
                _loginStepTwoLiveData.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                _loginStepTwoLiveData.setValue(null);
            }
        });
    }

    public void loginUser(LoginStepTwoResponseBody loginStepTwoResponseBody, UserDatabase database){
        loginStepTwoRemoteDataSource.loginUser(loginStepTwoResponseBody,database);
    }
}
