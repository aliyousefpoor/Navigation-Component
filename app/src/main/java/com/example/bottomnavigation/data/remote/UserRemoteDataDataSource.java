package com.example.bottomnavigation.data.remote;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.model.ProfileUpdate;
import com.example.bottomnavigation.data.model.UpdateProfileBody;
import com.example.bottomnavigation.data.model.UpdateResponseBody;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRemoteDataDataSource implements com.example.bottomnavigation.data.datasource.UserRemoteDataSource {
    private static final String TAG = "UserRemoteDataSource";
    private ApiService apiService;

    public UserRemoteDataDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void updateProfile(ProfileUpdate profileUpdate, final DataSourceListener<UpdateResponseBody> dataSourceListener) {
        UpdateProfileBody updateProfileBody = new UpdateProfileBody(profileUpdate.getNickname(), profileUpdate.getDate_of_birth(), profileUpdate.getGender());
        apiService.update("Token "+profileUpdate.getToken(),updateProfileBody).enqueue(new Callback<UpdateResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<UpdateResponseBody> call, @NotNull Response<UpdateResponseBody> response) {
                dataSourceListener.onResponse(response.body());

            }

            @Override
            public void onFailure(@NotNull Call<UpdateResponseBody> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }

    @Override
    public void getProfile(String token, final DataSourceListener<RemoteUser> dataSourceListener) {
        apiService.getUser("Token "+token).enqueue(new Callback<RemoteUser>() {
            @Override
            public void onResponse(@NotNull Call<RemoteUser> call, @NotNull Response<RemoteUser> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<RemoteUser> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }

}
