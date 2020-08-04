package com.example.bottomnavigation.data.datasource;

import android.util.Log;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.model.LoginBody;
import com.example.bottomnavigation.data.model.LoginResponseBody;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRemoteDataSource implements UserLoginDataSource {
    private static final String TAG = "UserSource";
    private ApiService apiService;

    public LoginRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void postNumber(String number, String androidId, String deviceModel, String deviceOs, final DataSourceListener<LoginResponseBody> dataSourceListener) {

        LoginBody loginBody = new LoginBody(number, androidId, deviceModel, deviceOs);
        apiService.login(loginBody).enqueue(new Callback<LoginResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponseBody> call, @NotNull Response<LoginResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataSourceListener.onResponse(response.body());
                        Log.d(TAG, "onResponse: " + response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponseBody> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
