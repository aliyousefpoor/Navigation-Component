package com.example.bottomnavigation.moretab;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.local.database.IsLoginListener;
import com.example.bottomnavigation.data.local.database.UserInformationListener;
import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.repository.UserRepository;

public class MoreViewModel extends ViewModel {
    private static final String TAG = "MoreViewModel";
    private UserRepository userRepository;


    public MoreViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private SingleLiveEvent<Boolean> _isLogin = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> isLogin = _isLogin;

    public void isLogin(Context context){
        userRepository.isLogin(context,new IsLoginListener() {
            @Override
            public void isLogin(Boolean isLogin) {
                if (isLogin){
                    _isLogin.postValue(true);
                }
                else {
                    _isLogin.postValue(false);
                }
            }

        });
    }
}
