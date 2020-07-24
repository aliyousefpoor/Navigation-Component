package com.example.bottomnavigation.data.datasource;

import android.util.Log;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.model.User;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSource implements UserDataSource {
    private static final String TAG = "UserSource";
    private ApiService apiService;

    public UserSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void postNumber(String number, String androidId, String deviceModel, String deviceOs, final DataSourceListener<User> dataSourceListener) {

        User user = new User(number, androidId, deviceModel, deviceOs);
        apiService.createUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataSourceListener.onResponse(response.body());
                        Log.d(TAG, "onResponse: " + response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
