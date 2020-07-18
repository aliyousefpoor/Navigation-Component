package com.example.bottomnavigation.data.datasource;

import android.util.Log;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.utils.ApiBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorySource {
    private static final String TAG = "RemoteDataSource";
    private ApiService apiService;
    private DataSourceListener<List<Category>> dataSourceListener;
    private static final CategorySource remoteInstance = new CategorySource();

    public static CategorySource getInstance(){
        return remoteInstance;
    }

    private CategorySource() {
        apiService = ApiBuilder.create(ApiService.class);
        Log.d(TAG, "RemoteDataSource: ");
    }



    public void categoryCallBack(DataSourceListener<List<Category>> dataSourceListener){
        this.dataSourceListener =dataSourceListener;
    }


    public void getCategory(){
        apiService.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NotNull Call<List<Category>> call, @NotNull Response<List<Category>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        dataSourceListener.onResponse(response.body());
                        Log.d(TAG, "onCategoryResponse: " +response.body().toString());
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
