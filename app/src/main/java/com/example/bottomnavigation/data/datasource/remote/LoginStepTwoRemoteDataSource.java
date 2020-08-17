package com.example.bottomnavigation.data.datasource.remote;

import android.content.Context;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.local.database.LoginAsyncTask;
import com.example.bottomnavigation.data.datasource.local.database.UserDatabase;
import com.example.bottomnavigation.data.model.LoginStepTwo;
import com.example.bottomnavigation.data.model.LoginStepTwoBody;
import com.example.bottomnavigation.data.model.LoginStepTwoResponseBody;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;

public class LoginStepTwoRemoteDataSource {
    private static final String TAG = "UserVerificationSource";
    private ApiService apiService;


    public LoginStepTwoRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public void loginStepTwo(LoginStepTwo loginStepTwo, final DataSourceListener<LoginStepTwoResponseBody> dataSourceListener) {
        LoginStepTwoBody verification = new LoginStepTwoBody(loginStepTwo.getNumber(),loginStepTwo.getAndroidId(),loginStepTwo.getCode());

        apiService.verification(verification).enqueue(new Callback<LoginStepTwoResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<LoginStepTwoResponseBody> call, @NotNull Response<LoginStepTwoResponseBody> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<LoginStepTwoResponseBody> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }

    public void userLogin(LoginStepTwoResponseBody loginStepTwoResponseBody, UserDatabase database){
        LoginAsyncTask loginAsyncTask = new LoginAsyncTask(loginStepTwoResponseBody, database);
        loginAsyncTask.execute();
    }
}
