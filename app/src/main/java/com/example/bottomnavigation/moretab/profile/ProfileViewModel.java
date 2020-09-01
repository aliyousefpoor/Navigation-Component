package com.example.bottomnavigation.moretab.profile;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.UpdateResponse;
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

    private SingleLiveEvent<UpdateResponse> _updateUserProfile = new SingleLiveEvent<>();
    public SingleLiveEvent<UpdateResponse> updateUserProfile = _updateUserProfile;

    private MutableLiveData<User> _getUser = new MutableLiveData<>();
    public MutableLiveData<User> getUser = _getUser;



    public void updateProfile(User user){
        profileRepository.updateProfile(user, new DataSourceListener<UpdateResponse>() {
            @Override
            public void onResponse(UpdateResponse response) {
                _updateUserProfile.postValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void updateImage(File file){
        profileRepository.updateImage(file, new DataSourceListener<UpdateResponse>() {
            @Override
            public void onResponse(UpdateResponse response) {
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
