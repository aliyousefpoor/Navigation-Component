package com.example.bottomnavigation.moretab;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.UserVerificationSource;
import com.example.bottomnavigation.data.model.UserVerification;

public class UserVerificationViewModel extends ViewModel {
    private static final String TAG = "UserVerificationViewMod";
    private UserVerificationSource verificationSource;

    public UserVerificationViewModel(UserVerificationSource verificationSource) {
        this.verificationSource = verificationSource;
    }

    private MutableLiveData<UserVerification> _verificationLiveData = new MutableLiveData<>();
    public LiveData<UserVerification> verificationLiveData = _verificationLiveData;

    public void postVerificationCode(String number, String androidId ,String code) {
        verificationSource.postCode( number ,androidId,code ,new DataSourceListener<UserVerification>() {
            @Override
            public void onResponse(UserVerification response) {
                _verificationLiveData.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }
}
