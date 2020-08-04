package com.example.bottomnavigation.moretab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.database.UserDataBase;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.repository.LoginRepository;

import java.util.List;

public class MoreViewModel extends ViewModel {
    private static final String TAG = "MoreViewModel";
    private LoginRepository loginRepository;


    public MoreViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    private MutableLiveData<Boolean> _isLoginUser = new MutableLiveData<>();
    public LiveData<Boolean> isLoginUser = _isLoginUser;

    public void isUserLogin(final Context context ,UserInformationListener userInformationListener ) {

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
