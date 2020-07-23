package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.data.model.Store;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {

    @GET("store/16")Call<Store> getStore();
    @GET("category/16/463")Call<List<Category>> getCategory();

}
