package com.example.bottomnavigation.data.datasource.remote;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListRemoteDataSource {
    private ApiService apiService;

    public ProductListRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getProductList(Integer id,int offset ,final DataSourceListener<List<Product>> dataSourceListener) {
        apiService.getProductList(id, 20, offset).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NotNull Call<List<Product>> call, @NotNull Response<List<Product>> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<Product>> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
