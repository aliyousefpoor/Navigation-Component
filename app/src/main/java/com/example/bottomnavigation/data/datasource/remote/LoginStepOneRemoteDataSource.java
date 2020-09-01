package com.example.bottomnavigation.data.datasource.remote;

import android.util.Log;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.LoginStepOneRequest;
import com.example.bottomnavigation.data.model.LoginStepOneResponse;

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

    public void loginStepOne(LoginStepOneRequest loginStepOneRequest1, final DataSourceListener<LoginStepOneResponse> dataSourceListener) {

        LoginStepOneRequest loginStepOneRequest = new LoginStepOneRequest(loginStepOneRequest1.getMobile()
                ,loginStepOneRequest1.getDevice_id(),loginStepOneRequest1.getDevice_model()
                ,loginStepOneRequest1.getDevice_os());

        apiService.loginStepOne(loginStepOneRequest).enqueue(new Callback<LoginStepOneResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginStepOneResponse> call, @NotNull Response<LoginStepOneResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataSourceListener.onResponse(response.body());
                        Log.d(TAG, "onResponse: " + response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<LoginStepOneResponse> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
