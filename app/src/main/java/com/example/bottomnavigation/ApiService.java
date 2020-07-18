package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Store;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {

    @GET("store/16")Call<Store> getStore();
}
