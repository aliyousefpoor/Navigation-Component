package com.example.bottomnavigation;

import com.example.bottomnavigation.homeTab.h_model.Store;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface {

    String JSONURL="https://api.vasapi.click/store/16";
    @GET("store/16")Call<Store> getString();
}
