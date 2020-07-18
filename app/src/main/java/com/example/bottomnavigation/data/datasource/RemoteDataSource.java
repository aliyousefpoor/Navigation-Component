package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.RemoteDataListener;
import com.example.bottomnavigation.data.model.Store;
import com.example.bottomnavigation.utils.ApiBuilder;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {
    private ApiService apiService;
    private RemoteDataListener remoteDataListener;
    private static final RemoteDataSource remoteInstance = new RemoteDataSource();

    public static RemoteDataSource getInstance(){
        return remoteInstance;
    }

    private RemoteDataSource() {
        apiService = ApiBuilder.create(ApiService.class);
    }

    public void remoteCallBack(RemoteDataListener remoteDataListener) {
        this.remoteDataListener=remoteDataListener;
    }

    public void getStore() {
        apiService.getStore().enqueue(new Callback<Store>() {
            @Override
            public void onResponse(@NotNull Call<Store> call, @NotNull Response<Store> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        remoteDataListener.onResponse(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<Store> call, @NotNull Throwable t) {
                remoteDataListener.onFailure(t);
            }
        });
    }
}
