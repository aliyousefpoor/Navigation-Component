package com.example.bottomnavigation.data.datasource;

import android.util.Log;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.model.LoginBody;
import com.example.bottomnavigation.data.model.ResponseLoginBody;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginSource implements UserLoginDataSource {
    private static final String TAG = "UserSource";
    private ApiService apiService;

    public LoginSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void postNumber(String number, String androidId, String deviceModel, String deviceOs, final DataSourceListener<ResponseLoginBody> dataSourceListener) {

        LoginBody loginBody = new LoginBody(number, androidId, deviceModel, deviceOs);
        apiService.createUser(loginBody).enqueue(new Callback<ResponseLoginBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseLoginBody> call, @NotNull Response<ResponseLoginBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataSourceListener.onResponse(response.body());
                        Log.d(TAG, "onResponse: " + response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseLoginBody> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
