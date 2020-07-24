package com.example.bottomnavigation.moretab;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.UserSource;
import com.example.bottomnavigation.data.model.User;

public class UserViewModel extends ViewModel {
    private static final String TAG = "UserViewModel";
    private UserSource userSource;

    public UserViewModel(UserSource userSource){
        this.userSource=userSource;
    }

    MutableLiveData<User> _userLiveData = new MutableLiveData<>();
    LiveData<User> userLiveData = _userLiveData;


    public void postUserNumber( String number ,String androidId,String deviceModel,String deviceOs ){

        userSource.postNumber(number,androidId,deviceModel,deviceOs,new DataSourceListener<User>() {
            @Override
            public void onResponse(User response) {
                _userLiveData.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        }
        );


    }
}
