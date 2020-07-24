package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.model.UserVerification;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserVerificationSource implements UserVerificationDataSource {
    private static final String TAG = "UserVerificationSource";
    private ApiService apiService;

    public UserVerificationSource(ApiService apiService) {
        this.apiService = apiService;
    }


    @Override
    public void postCode( String number ,String androidId, String code ,final DataSourceListener<UserVerification> dataSourceListener) {
        UserVerification verification = new UserVerification(number,androidId,code);
        apiService.postCode(verification).enqueue(new Callback<UserVerification>() {
            @Override
            public void onResponse(@NotNull Call<UserVerification> call, @NotNull Response<UserVerification> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<UserVerification> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
