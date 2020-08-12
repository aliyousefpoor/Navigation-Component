package com.example.bottomnavigation.data.remote;

import android.util.Log;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.LoginStepOneBody;
import com.example.bottomnavigation.data.model.LoginResponseBody;
import com.example.bottomnavigation.data.model.LoginStepOne;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginStepOneRemoteDataSource {
    private static final String TAG = "UserSource";
    private ApiService apiService;

    public LoginStepOneRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public void loginStepOne(LoginStepOne loginStepOne, final DataSourceListener<LoginResponseBody> dataSourceListener) {

        LoginStepOneBody loginStepOneBody = new LoginStepOneBody(loginStepOne.getNumber(),loginStepOne.getAndroidId(),
                loginStepOne.getDeviceModel(),loginStepOne.getDeviceOs());

        apiService.login(loginStepOneBody).enqueue(new Callback<LoginResponseBody>() {
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
