package com.example.bottomnavigation.moretab.profile;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.local.database.UserDatabase;
import com.example.bottomnavigation.data.model.UpdateResponseBody;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.repository.ProfileRepository;
import com.example.bottomnavigation.moretab.SingleLiveEvent;

import java.io.File;



public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private ProfileRepository profileRepository;

    public ProfileViewModel(ProfileRepository profileRepository){
        this.profileRepository = profileRepository;
    }

    private SingleLiveEvent<UpdateResponseBody> _updateUserProfile = new SingleLiveEvent<>();
    public SingleLiveEvent<UpdateResponseBody> updateUserProfile = _updateUserProfile;

    private MutableLiveData<User> _getUser = new MutableLiveData<>();
    public MutableLiveData<User> getUser = _getUser;



    public void updateProfile(User user){
        profileRepository.updateProfile(user, new DataSourceListener<UpdateResponseBody>() {
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

    public void updateImage(String token ,File file){
        profileRepository.updateImage(token ,file, new DataSourceListener<UpdateResponseBody>() {
            @Override
            public void onResponse(UpdateResponseBody response) {
                Log.d(TAG, "onResponse: "+response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: updateImage");
            }
        });
    }

    public void getUser(){
        profileRepository.getProfile( new DataSourceListener<User>() {

            @Override
            public void onResponse(User response) {
                _getUser.postValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
                _getUser.postValue(null);
            }
        });
    }
}
