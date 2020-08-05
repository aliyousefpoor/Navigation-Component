package com.example.bottomnavigation.moretab;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.repository.IsLoginRepository;

public class MoreViewModel extends ViewModel {
    private static final String TAG = "MoreViewModel";
    private IsLoginRepository isLoginRepository;


    public MoreViewModel(IsLoginRepository isLoginRepository) {
        this.isLoginRepository = isLoginRepository;
    }

    private SingleLiveEvent<UserEntity> _isLoginUser = new SingleLiveEvent<>();
    public SingleLiveEvent<UserEntity> isLoginUser = _isLoginUser;


    public void isUserLogin(final Context context ) {
        isLoginRepository.getUser(context, new UserInformationListener() {
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
