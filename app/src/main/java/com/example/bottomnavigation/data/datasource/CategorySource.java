package com.example.bottomnavigation.data.datasource;

import android.util.Log;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.model.Category;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorySource {
    private static final String TAG = "RemoteDataSource";
    private ApiService apiService;


    public CategorySource(ApiService apiService) {
        this.apiService = apiService;

    }

    public void getCategory(final DataSourceListener<List<Category>> dataSourceListener) {
        apiService.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NotNull Call<List<Category>> call, @NotNull Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataSourceListener.onResponse(response.body());
                        Log.d(TAG, "onCategoryResponse: " + response.body().toString());
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Category>> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });

    }
}
