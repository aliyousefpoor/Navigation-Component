package com.example.bottomnavigation.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;

import com.example.bottomnavigation.data.model.LoginStepTwo;
import com.example.bottomnavigation.data.model.VerificationResponseBody;
import com.example.bottomnavigation.data.repository.LoginRepository;

public class VerificationViewModel extends ViewModel {
    private static final String TAG = "UserVerificationViewMod";
    private LoginRepository loginRepository;

    public VerificationViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    private MutableLiveData<VerificationResponseBody> _verificationLiveData = new MutableLiveData<>();
    public LiveData<VerificationResponseBody> verificationLiveData = _verificationLiveData;

    public void loginStepTwo(LoginStepTwo loginStepTwo) {
        loginRepository.loginStep2(loginStepTwo, new DataSourceListener<VerificationResponseBody>() {

            @Override
            public void onResponse(VerificationResponseBody response) {
                _verificationLiveData.setValue(response);

                Log.d(TAG, "onResponse:Verification ");

            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: " + throwable);
            }

        });

    }


}
