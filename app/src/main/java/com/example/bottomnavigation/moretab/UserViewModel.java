package com.example.bottomnavigation.moretab;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.LoginSource;
import com.example.bottomnavigation.data.model.ResponseLoginBody;

public class UserViewModel extends ViewModel {
    private static final String TAG = "UserViewModel";
    private LoginSource loginSource;

    public UserViewModel(LoginSource loginSource){
        this.loginSource = loginSource;
    }

    MutableLiveData<ResponseLoginBody> _userLiveData = new MutableLiveData<>();
    LiveData<ResponseLoginBody> userLiveData = _userLiveData;


    public void postUserNumber( String number ,String androidId,String deviceModel,String deviceOs ){

        loginSource.postNumber(number,androidId,deviceModel,deviceOs,new DataSourceListener<ResponseLoginBody>() {
            @Override
            public void onResponse(ResponseLoginBody response) {
                _userLiveData.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }

        }
        );


    }
}
