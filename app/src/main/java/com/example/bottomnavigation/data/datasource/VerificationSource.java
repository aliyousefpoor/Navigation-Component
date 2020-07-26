package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.model.VerificationBody;
import com.example.bottomnavigation.data.model.VerificationResponseBody;

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
    public void postCode(String number, String androidId, String code, final DataSourceListener<VerificationResponseBody> dataSourceListener) {
        VerificationBody verification = new VerificationBody(number, androidId, code);
        apiService.postCode(verification).enqueue(new Callback<VerificationResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<VerificationResponseBody> call, @NotNull Response<VerificationResponseBody> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<VerificationResponseBody> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
