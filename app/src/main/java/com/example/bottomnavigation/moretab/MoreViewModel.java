package com.example.bottomnavigation.moretab;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.repository.LoginRepository;

public class MoreViewModel extends ViewModel {
    private static final String TAG = "MoreViewModel";
    private LoginRepository loginRepository;


    public MoreViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    private MutableLiveData<Boolean> _isLoginUser = new MutableLiveData<>();
    public LiveData<Boolean> isLoginUser = _isLoginUser;

    public void isUserLogin(final Context context ,UserInformationListener userInformationListener ) {
        //why loginrepository?
        loginRepository.getInformation(context,userInformationListener);
//        loginRepository.getInformation(context, new UserInformationListener() {
//            @Override
//            public void onUserInformation(User user) {
//
//                if (user==null){
//                    _isLoginUser.setValue(true);
//                }
//                assert user != null;
//                Log.d(TAG, "onSaveUser: "+user.getName());
//
//
//            }
//
//        });

    }
//            @Override
//            public void onSaveUser(User user) {
//                if (user!=null){
//                    _isLoginUser.setValue(true);
//                }
//                else {
//                    _isLoginUser.setValue(false);
//                }
//            }
//        });


}
