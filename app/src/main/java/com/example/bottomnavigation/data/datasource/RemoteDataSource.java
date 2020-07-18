package com.example.bottomnavigation.data.datasource;

import android.util.Log;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.RemoteCategoryListener;
import com.example.bottomnavigation.RemoteStoreListener;
import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.data.model.Store;
import com.example.bottomnavigation.utils.ApiBuilder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {
    private static final String TAG = "RemoteDataSource";
    private ApiService apiService;
    private RemoteStoreListener remoteStoreListener;
    private RemoteCategoryListener remoteCategoryListener;
    private static final RemoteDataSource remoteInstance = new RemoteDataSource();

    public static RemoteDataSource getInstance(){
        return remoteInstance;
    }

    private RemoteDataSource() {
        apiService = ApiBuilder.create(ApiService.class);
        Log.d(TAG, "RemoteDataSource: ");
    }

    public void remoteStoreCallBack(RemoteStoreListener remoteStoreListener) {
        this.remoteStoreListener=remoteStoreListener;
    }

    public void remoteCategoryCallBack(RemoteCategoryListener remoteCategoryListener){
        this.remoteCategoryListener =remoteCategoryListener;
    }



    public void getStore() {
        apiService.getStore().enqueue(new Callback<Store>() {
            @Override
            public void onResponse(@NotNull Call<Store> call, @NotNull Response<Store> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        remoteStoreListener.onStoreResponse(response.body());
                        Log.d(TAG, "onResponse: " +response.body().toString());

                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<Store> call, @NotNull Throwable t) {
                remoteStoreListener.onStoreFailure(t);
            }
        });
    }

    public void getCategory(){
        apiService.getCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NotNull Call<List<Category>> call, @NotNull Response<List<Category>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        remoteCategoryListener.onCategoryResponse(response.body());
                        Log.d(TAG, "onCategoryResponse: " +response.body().toString());
                    }
                }

            }

            @Override
            public void onFailure(@NotNull Call<List<Category>> call, @NotNull Throwable t) {
                remoteCategoryListener.onCategoryFailure(t);
            }
        });

    }
}
