package com.example.bottomnavigation;

import android.util.Log;
import android.util.Printer;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppViewModel extends ViewModel {
    private static final String TAG = "AppViewModel";

    public AppViewModel() {
        getData();
    }

    private MutableLiveData<Store> storeListLivedata = new MutableLiveData<>();

    public MutableLiveData<Store> getStoreList() {
        return storeListLivedata;
    }

    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    private MutableLiveData<Boolean> errorStateLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getErrorStateLiveData() {
        return errorStateLiveData;
    }


    public void getData() {
        Log.d(TAG, "getData: ");
        loadingLiveData.setValue(true);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.vasapi.click/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        Interface api = retrofit.create(Interface.class);
        Call<Store> call = api.getString();

        Log.d(TAG, "getData: ");
        call.enqueue(new Callback<Store>() {
            @Override
            public void onResponse(Call<Store> call, Response<Store> response) {
                loadingLiveData.setValue(false);
                errorStateLiveData.setValue(false);

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        storeListLivedata.setValue(response.body());
                    } else {
                        errorStateLiveData.setValue(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<Store> call, Throwable t) {

                loadingLiveData.setValue(false);
                errorStateLiveData.setValue(true);

                t.printStackTrace();
            }
        });
    }
}
