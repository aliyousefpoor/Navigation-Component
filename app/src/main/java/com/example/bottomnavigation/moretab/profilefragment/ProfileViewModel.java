package com.example.bottomnavigation.moretab.profilefragment;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.local.database.UserInformationListener;
import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.model.UpdateResponseBody;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.repository.UserRepository;
import com.example.bottomnavigation.moretab.SingleLiveEvent;

import java.io.File;



public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private UserRepository userRepository;

    public ProfileViewModel(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private SingleLiveEvent<UpdateResponseBody> _updateUserProfile = new SingleLiveEvent<>();
    public SingleLiveEvent<UpdateResponseBody> updateUserProfile = _updateUserProfile;

    private MutableLiveData<User> _getUserProfile = new MutableLiveData<>();
    public MutableLiveData<User> getUserProfile = _getUserProfile;
    private MutableLiveData<User> _getUser = new MutableLiveData<>();
    public MutableLiveData<User> getUser = _getUser;



    public void updateProfile(User user, Context context){
        userRepository.updateProfile(user,context, new DataSourceListener<UpdateResponseBody>() {
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
        userRepository.getProfile(token,context, new DataSourceListener<User>() {

            @Override
            public void onResponse(User response) {
//                _getUserProfile.postValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void updateImage(String token ,File file){
        userRepository.updateImage(token ,file, new DataSourceListener<UpdateResponseBody>() {
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

    public void getUser(Context context){
        userRepository.getUser(context, new DataSourceListener<User>() {

            @Override
            public void onResponse(User response) {
                _getUser.postValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
