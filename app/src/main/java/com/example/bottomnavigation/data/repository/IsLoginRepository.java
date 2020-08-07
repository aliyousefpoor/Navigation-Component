package com.example.bottomnavigation.data.repository;

import android.content.Context;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.model.ProfileUpdate;
import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.model.UpdateResponseBody;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.remote.UserRemoteDataSource;
import com.example.bottomnavigation.moretab.UserInformationListener;

public class IsLoginRepository {
    private UserLocaleDataSourceImpl userLocaleDataSourceImpl;
    private UserRemoteDataSource userRemoteDataSource;

    public IsLoginRepository(UserLocaleDataSourceImpl userLocaleDataSourceImpl,UserRemoteDataSource userRemoteDataSource) {
        this.userLocaleDataSourceImpl = userLocaleDataSourceImpl;
        this.userRemoteDataSource = userRemoteDataSource;
    }

//    public IsLoginRepository(UserRemoteDataSource userRemoteDataSource) {
//        this.userRemoteDataSource = userRemoteDataSource;
//    }

    public void saveUser(User user, Context context) {
        userLocaleDataSourceImpl.saveUser(user, context);
    }

    public void getUser(Context context, UserInformationListener userInformationListener) {
        userLocaleDataSourceImpl.getUser(context, userInformationListener);
    }

    public void updateProfile(final ProfileUpdate profileUpdate, final Context context, final DataSourceListener<UpdateResponseBody> dataSourceListener){
        userRemoteDataSource.updateProfile(profileUpdate, new DataSourceListener<UpdateResponseBody>() {
            @Override
            public void onResponse(UpdateResponseBody response) {
                User user = new User();
                user.setGender(response.getData().getGender());
                user.setDate(response.getData().getBirthdayDate());
                user.setName(response.getData().getNickName());
                user.setToken(profileUpdate.getToken());
                saveUser(user,context);
                dataSourceListener.onResponse(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                dataSourceListener.onFailure(throwable);
            }
        });

    }
    public void getProfile(String token, DataSourceListener<RemoteUser> dataSourceListener){
        userRemoteDataSource.getProfile(token,dataSourceListener);

    }

//    public void saveUserProfile(DataSourceListener< RemoteUser> dataSourceListener){
//        userLocaleDataSourceImpl.
//    }

}
