package com.example.bottomnavigation.moretab.profilefragment;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.repository.LoginRepository;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";

    private LoginRepository loginRepository;

    public ProfileViewModel(LoginRepository loginRepository){
        this.loginRepository=loginRepository;
    }

    private MutableLiveData<User> _userLiveData = new MutableLiveData<>();
    public LiveData<User> userLiveData = _userLiveData;

    public void userInformation(int id, String token , String name, String date , String gender , Context context){
        loginRepository.saveUser(id,token,name ,date,gender , context);
    }

}
