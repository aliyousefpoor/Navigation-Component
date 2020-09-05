package com.example.bottomnavigation.data.datasource.remote;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.ProductsList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListRemoteDataSource {
    private ApiService apiService;

    public ProductListRemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getProductList(Integer id,int offset ,final DataSourceListener<List<ProductsList>> dataSourceListener) {
        apiService.getProductList(id, 20, offset).enqueue(new Callback<List<ProductsList>>() {
            @Override
            public void onResponse(Call<List<ProductsList>> call, Response<List<ProductsList>> response) {
                dataSourceListener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductsList>> call, Throwable t) {
                dataSourceListener.onFailure(t);
            }
        });
    }
}
