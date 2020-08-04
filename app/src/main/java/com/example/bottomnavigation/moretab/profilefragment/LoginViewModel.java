package com.example.bottomnavigation.moretab.profilefragment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.LoginResponseBody;
import com.example.bottomnavigation.data.repository.LoginRepository;

//change package
public class LoginViewModel extends ViewModel {
    private static final String TAG = "UserViewModel";

    private LoginRepository loginRepository;

    public LoginViewModel(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }


    MutableLiveData<LoginResponseBody> _userLiveData = new MutableLiveData<>();
    LiveData<LoginResponseBody> userLiveData = _userLiveData;



    public void loginStepOne(String number, String androidId, String deviceModel, String deviceOs) {


        loginRepository.loginStep1(number, androidId, deviceModel, deviceOs, new DataSourceListener<LoginResponseBody>() {
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
