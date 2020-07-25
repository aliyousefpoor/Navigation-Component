package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.model.LoginVerificationBody;
import com.example.bottomnavigation.data.model.ResponseVerificationBody;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;

import retrofit2.Response;

public class VerificationSource implements LoginVerificationDataSource {
    private static final String TAG = "UserVerificationSource";
    private ApiService apiService;

    public VerificationSource(ApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public void postCode(String number, String androidId, String code, final DataSourceListener<ResponseVerificationBody> dataSourceListener) {
        LoginVerificationBody verification = new LoginVerificationBody(number, androidId, code);
        apiService.postCode(verification).enqueue(new Callback<ResponseVerificationBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseVerificationBody> call, @NotNull Response<ResponseVerificationBody> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ResponseVerificationBody> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
