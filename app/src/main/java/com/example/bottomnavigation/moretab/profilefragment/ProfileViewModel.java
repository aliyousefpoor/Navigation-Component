package com.example.bottomnavigation.moretab.profilefragment;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.ProfileUpdate;
import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.model.UpdateResponseBody;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.repository.IsLoginRepository;
import com.example.bottomnavigation.moretab.SingleLiveEvent;


public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private IsLoginRepository isLoginRepository;

    public ProfileViewModel(IsLoginRepository isLoginRepository){
        this.isLoginRepository=isLoginRepository;
    }

    private SingleLiveEvent<UpdateResponseBody> _updateUserProfile = new SingleLiveEvent<>();
    public SingleLiveEvent<UpdateResponseBody> updateUserProfile = _updateUserProfile;

    private MutableLiveData<RemoteUser> _getProfile = new MutableLiveData<>();
    public MutableLiveData<RemoteUser> getUserProfile = _getProfile;

    public void saveUser(User user, Context context){
        isLoginRepository.saveUser(user , context);
    }



    public void updateProfile(ProfileUpdate profileUpdate){
        isLoginRepository.updateProfile(profileUpdate, new DataSourceListener<UpdateResponseBody>() {
            @Override
            public void onResponse(UpdateResponseBody response) {
                _updateUserProfile.postValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }


    public void getProfile(String token,Context context){
        isLoginRepository.getProfile(token ,context, new DataSourceListener<RemoteUser>() {
            @Override
            public void onResponse(RemoteUser response) {
                _getProfile.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

}
