package com.example.bottomnavigation.moretab;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.local.database.UserInformationListener;
import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.repository.UserRepository;

public class MoreViewModel extends ViewModel {
    private static final String TAG = "MoreViewModel";
    private UserRepository userRepository;


    public MoreViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private SingleLiveEvent<UserEntity> _isLoginUser = new SingleLiveEvent<>();
    public SingleLiveEvent<UserEntity> isLoginUser = _isLoginUser;


    public void isUserLogin(final Context context ) {
        userRepository.getUser(context, new UserInformationListener() {
            @Override
            public void onCheckUser(UserEntity userEntity) {
               if (userEntity==null){
                   _isLoginUser.postValue(null);
               }
               else{
                   _isLoginUser.postValue(userEntity);
               }
            }
        });
    }
}
