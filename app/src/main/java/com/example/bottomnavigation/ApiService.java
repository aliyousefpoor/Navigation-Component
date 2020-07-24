package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.data.model.Store;
import com.example.bottomnavigation.data.model.User;
import com.example.bottomnavigation.data.model.UserVerification;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {

    @GET("store/16")Call<Store> getStore();
    @GET("category/16/463")Call<List<Category>> getCategory();
    @POST("mobile_login_step1/16")Call<User> createUser(@Body User user);
    @POST("mobile_login_step1/16")Call<UserVerification> postCode(@Body UserVerification verification);

}
