package com.example.bottomnavigation.data.datasource.remote;

import android.util.Log;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.ProfileResponse;
import com.example.bottomnavigation.data.model.UpdateProfile;
import com.example.bottomnavigation.data.model.UpdateResponse;
import com.example.bottomnavigation.data.model.User;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRemoteDataSourceImpl {
    private static final String TAG = "UserRemoteDataSource";
    private ApiService apiService;

    public UserRemoteDataSourceImpl(ApiService apiService) {
        this.apiService = apiService;
    }


    public void updateProfile(User user, final DataSourceListener<UpdateResponse> dataSourceListener) {
        UpdateProfile updateProfile = new UpdateProfile(user.getName(),
                user.getDate(), user.getGender());
        apiService.update(updateProfile).enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(@NotNull Call<UpdateResponse> call, @NotNull Response<UpdateResponse> response) {
                dataSourceListener.onResponse(response.body());

            }

            @Override
            public void onFailure(@NotNull Call<UpdateResponse> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }


    public void getProfile(final String token, final DataSourceListener<User> dataSourceListener) {
        Log.d(TAG, "getProfile: "+token);
        apiService.getUser().enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(@NotNull Call<ProfileResponse> call, @NotNull Response<ProfileResponse> response) {
                User user = new User();
                user.setUserId(response.body().getId());
                user.setName(response.body().getNickName());
                user.setDate(response.body().getBirthdayDate());
                user.setGender(response.body().getGender());
                user.setAvatar(response.body().getAvatar());
                user.setToken(token);
                dataSourceListener.onResponse(user);

            }

            @Override
            public void onFailure(@NotNull Call<ProfileResponse> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }


    public void updateImage( File file, final DataSourceListener<UpdateResponse> dataSourceListener) {

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part requestImage = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);

        apiService.updateImage(requestImage).enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(@NotNull Call<UpdateResponse> call, @NotNull Response<UpdateResponse> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<UpdateResponse> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
                Log.d(TAG, "onFailure: updateImage");
            }
        });
    }
}
