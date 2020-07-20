package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.model.Store;


import org.jetbrains.annotations.NotNull;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeSource implements HomeDataSource {
    private static final String TAG = "StoreSource";
    private ApiService apiService;

    public HomeSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getStore(final DataSourceListener<Store> dataSourceListener) {
        apiService.getStore().enqueue(new Callback<Store>() {
            @Override
            public void onResponse(@NotNull Call<Store> call, @NotNull Response<Store> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataSourceListener.onResponse(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<Store> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });

    }
}
