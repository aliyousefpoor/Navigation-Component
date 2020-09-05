package com.example.bottomnavigation.data.datasource.remote;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.ProductsList;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailRemoteDataSource {
    private ApiService apiService;

    public ProductDetailRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getProductDetail(int id, final DataSourceListener<ProductsList> dataSourceListener) {
        apiService.getProductDetail(id).enqueue(new Callback<ProductsList>() {
            @Override
            public void onResponse(@NotNull Call<ProductsList> call, @NotNull Response<ProductsList> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<ProductsList> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
