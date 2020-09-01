package com.example.bottomnavigation.data.datasource.remote;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.local.database.LoginAsyncTask;
import com.example.bottomnavigation.data.datasource.local.database.UserDao;
import com.example.bottomnavigation.data.model.LoginStepTwo;
import com.example.bottomnavigation.data.model.LoginStepTwoRequest;
import com.example.bottomnavigation.data.model.LoginStepTwoResponse;

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

    public void loginStepTwo(LoginStepTwoRequest loginStepTwo, final DataSourceListener<LoginStepTwoResponse> dataSourceListener) {
        LoginStepTwoRequest verification = new LoginStepTwoRequest(loginStepTwo.getMobile(),loginStepTwo.getDevice_id(),loginStepTwo.getVerification_code());

        apiService.verification(verification).enqueue(new Callback<LoginStepTwoResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginStepTwoResponse> call, @NotNull Response<LoginStepTwoResponse> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<LoginStepTwoResponse> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
