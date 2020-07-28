package com.example.bottomnavigation.moretab;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;

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

    public void postVerificationCode(String number, String androidId, String code) {
        loginRepository.repoPostCode(number, androidId, code, new DataSourceListener<VerificationResponseBody>() {

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
