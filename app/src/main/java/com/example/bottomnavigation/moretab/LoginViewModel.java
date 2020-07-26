package com.example.bottomnavigation.moretab;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.LoginSource;
import com.example.bottomnavigation.data.model.LoginResponseBody;

public class LoginViewModel extends ViewModel {
    private static final String TAG = "UserViewModel";

    private LoginSource loginSource;

    public LoginViewModel(LoginSource loginSource){
        this.loginSource = loginSource;
    }


    MutableLiveData<LoginResponseBody> _userLiveData = new MutableLiveData<>();
    LiveData<LoginResponseBody> userLiveData = _userLiveData;



    public void postUserNumber(String number, String androidId, String deviceModel, String deviceOs) {


        loginSource.postNumber(number, androidId, deviceModel, deviceOs, new DataSourceListener<LoginResponseBody>() {
            @Override
            public void onResponse(LoginResponseBody response) {
                _userLiveData.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }
}
