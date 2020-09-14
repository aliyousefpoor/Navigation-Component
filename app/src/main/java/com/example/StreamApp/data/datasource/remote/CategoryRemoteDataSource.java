package com.example.StreamApp.data.datasource.remote;



import com.example.StreamApp.ApiService;
import com.example.StreamApp.data.datasource.DataSourceListener;
import com.example.StreamApp.data.model.Category;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRemoteDataSource  {
    private static final String TAG = "RemoteDataSource";
    private ApiService apiService;


    public CategoryRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;

    }

    public void getCategory(final DataSourceListener<List<Category>> dataSourceListener) {
        apiService.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NotNull Call<List<Category>> call, @NotNull Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataSourceListener.onResponse(response.body());
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
