package com.example.bottomnavigation.data.datasource.remote;

import android.util.Log;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.LoginStepOneRequest;
import com.example.bottomnavigation.data.model.LoginStepOneResponse;
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

    public void loginStepOne(LoginStepOneRequest loginStepOne, final DataSourceListener<LoginStepOneResponse> dataSourceListener) {

        LoginStepOneRequest loginStepOneRequest = new LoginStepOneRequest(loginStepOne.getMobile(),loginStepOne.getDevice_id(),
                loginStepOne.getDevice_model(),loginStepOne.getDevice_os());

        apiService.login(loginStepOneRequest).enqueue(new Callback<LoginStepOneResponse>() {
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
