package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.data.model.Comment;
import com.example.bottomnavigation.data.model.LoginStepOneRequest;
import com.example.bottomnavigation.data.model.LoginStepOneResponse;

import com.example.bottomnavigation.data.model.Product;
import com.example.bottomnavigation.data.model.ProfileResponse;
import com.example.bottomnavigation.data.model.UpdateProfile;
import com.example.bottomnavigation.data.model.UpdateResponse;
import com.example.bottomnavigation.data.model.LoginStepTwoResponse;
import com.example.bottomnavigation.data.model.Store;
import com.example.bottomnavigation.data.model.LoginStepTwoRequest;

import java.util.List;

import okhttp3.MultipartBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

    @GET("store/16")
    Call<Store> getStore();

    @GET("category/16/463")
    Call<List<Category>> getCategory();

    @POST("mobile_login_step1/16")
    Call<LoginStepOneResponse> loginStepOne(@Body LoginStepOneRequest loginStepOneRequest);

    @POST("mobile_login_step2/16")
    Call<LoginStepTwoResponse> loginStepTwo(@Body LoginStepTwoRequest loginStepTwoRequest);

    @GET("profile")
    Call<ProfileResponse> getUser();

    @POST("profile")
    Call<UpdateResponse> update(@Body UpdateProfile updateProfile);

    @Multipart
    @POST("profile")
    Call<UpdateResponse> updateImage(@Part MultipartBody.Part avatar);

    @GET("listproducts/{categoryId}")
    Call<List<Product>> getProductList(@Path("categoryId") int categoryId,
                                            @Query("limit") int limit,
                                            @Query("offset") int offset);
    @GET("product/{productId}?device_os=ios")
    Call<Product> getProductDetail(@Path("productId") int productId);
    @GET("comment/{productId}")
    Call<List<Comment>> getComment(@Path("productId") int productId);
}
