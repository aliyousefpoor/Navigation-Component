package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.data.model.LoginBody;
import com.example.bottomnavigation.data.model.LoginResponseBody;
import com.example.bottomnavigation.data.model.RemoteUser;
import com.example.bottomnavigation.data.model.UpdateProfileBody;
import com.example.bottomnavigation.data.model.UpdateResponseBody;
import com.example.bottomnavigation.data.model.VerificationResponseBody;
import com.example.bottomnavigation.data.model.Store;
import com.example.bottomnavigation.data.model.VerificationBody;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
    @POST("mobile_login_step1/16")Call<LoginResponseBody> login(@Body LoginBody loginBody);
    @POST("mobile_login_step2/16")Call<VerificationResponseBody> verification(@Body VerificationBody verification);
    @GET("profile")Call<RemoteUser> getUser(@Header("Authorization") String token);
    @POST("profile")Call<UpdateResponseBody> update(@Header("Authorization") String token, @Body UpdateProfileBody updateProfileBody);
    @Multipart
    @POST("profile")Call<UpdateResponseBody> updateImage(@Header("Authorization") String token,@Part MultipartBody.Part avatar);
}
