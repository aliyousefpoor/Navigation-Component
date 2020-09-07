package com.example.bottomnavigation.data.datasource.remote;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.Comment;
import com.example.bottomnavigation.data.model.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailRemoteDataSource {
    private ApiService apiService;

    public ProductDetailRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getProductDetail(int id, final DataSourceListener<Product> dataSourceListener) {
        apiService.getProductDetail(id).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NotNull Call<Product> call, @NotNull Response<Product> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<Product> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }

    public void getProductComment(int id, final DataSourceListener<List<Comment>> dataSourceListener) {
        apiService.getComment(id).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(@NotNull Call<List<Comment>> call, @NotNull Response<List<Comment>> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<List<Comment>> call, @NotNull Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
