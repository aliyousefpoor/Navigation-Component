package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.data.model.LoginStepOneBody;
import com.example.bottomnavigation.data.model.LoginResponseBody;
import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.model.UpdateProfileBody;
import com.example.bottomnavigation.data.model.UpdateResponseBody;
import com.example.bottomnavigation.data.model.LoginStepTwoResponseBody;
import com.example.bottomnavigation.data.model.Store;
import com.example.bottomnavigation.data.model.LoginStepTwoBody;

import java.util.List;

import okhttp3.MultipartBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiService {

    @GET("store/16")Call<Store> getStore();
    @GET("category/16/463")Call<List<Category>> getCategory();
    @POST("mobile_login_step1/16")Call<LoginResponseBody> login(@Body LoginStepOneBody loginStepOneBody);
    @POST("mobile_login_step2/16")Call<LoginStepTwoResponseBody> verification(@Body LoginStepTwoBody verification);
    @GET("profile")Call<RemoteUser> getUser(@Header("Authorization") String token);
    @POST("profile")Call<UpdateResponseBody> update(@Header("Authorization") String token, @Body UpdateProfileBody updateProfileBody);
    @Multipart
    @POST("profile")Call<UpdateResponseBody> updateImage(@Header("Authorization") String token,@Part MultipartBody.Part avatar);
}
