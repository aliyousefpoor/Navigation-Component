package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.data.model.LoginBody;
import com.example.bottomnavigation.data.model.ResponseLoginBody;
import com.example.bottomnavigation.data.model.ResponseVerificationBody;
import com.example.bottomnavigation.data.model.Store;
import com.example.bottomnavigation.data.model.LoginVerificationBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiService {

    @GET("store/16")Call<Store> getStore();
    @GET("category/16/463")Call<List<Category>> getCategory();
    @POST("mobile_login_step1/16")Call<ResponseLoginBody> createUser(@Body LoginBody loginBody);
    @POST("mobile_login_step2/16")Call<ResponseVerificationBody> postCode(@Body LoginVerificationBody verification);

}
