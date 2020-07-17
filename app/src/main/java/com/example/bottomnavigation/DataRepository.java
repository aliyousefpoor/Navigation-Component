package com.example.bottomnavigation;


import android.util.Log;


import com.example.bottomnavigation.data.model.Store;
import com.example.bottomnavigation.utils.ApiBuilder;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {
    private static final String TAG = "DataRepository";
    private ApiService api;
    private CallBackListener callBackListener ;
    private static final DataRepository ourInstance = new DataRepository();


    public static DataRepository getInstance() {
        return ourInstance;
    }

    private DataRepository() {
        api = ApiBuilder.create(ApiService.class);
        Log.d(TAG, "DataRepository: Constructor");
    }

    public void onCallBackListener(CallBackListener callBackListener) {
        this.callBackListener = callBackListener;

    }




    public void getStore() {
        api.getString().enqueue(new Callback<Store>() {
            @Override
            public void onResponse(@NotNull Call<Store> call, @NotNull Response<Store> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        callBackListener.onResponse(response.body());
                    }
                }

                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(@NotNull Call<Store> call, @NotNull Throwable t) {
                Log.d(TAG, "onFailure: ");

                callBackListener.onFailure(t);

            }
        });

    }
}
