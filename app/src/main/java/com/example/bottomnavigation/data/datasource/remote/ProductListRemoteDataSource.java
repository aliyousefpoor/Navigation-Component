package com.example.bottomnavigation.data.datasource.remote;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.ListProducts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListRemoteDataSource {
    private ApiService apiService;

    public ProductListRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getProductList(Integer id, final DataSourceListener<List<ListProducts>> dataSourceListener) {
        apiService.getProductList(id, 20, 0).enqueue(new Callback<List<ListProducts>>() {
            @Override
            public void onResponse(Call<List<ListProducts>> call, Response<List<ListProducts>> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<ListProducts>> call, Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
