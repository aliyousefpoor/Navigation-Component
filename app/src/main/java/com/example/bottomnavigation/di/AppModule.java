package com.example.bottomnavigation.di;

import com.example.bottomnavigation.utils.AppConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppModule {
//    private String token;
//
//    public AppModule(String token){
//        this.token=token;
//    }

    private Retrofit retrofit = null;


    public Retrofit provideRetrofit() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);
//        httpClient.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request newRequest =chain.request().newBuilder()
//                        .addHeader("Authorization" ,"Token "+token).build();
//                return chain.proceed(newRequest);
//            }
//        }).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(AppConstants.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()).client(httpClient.connectTimeout(90, TimeUnit.SECONDS).readTimeout(90, TimeUnit.SECONDS) .writeTimeout(90, TimeUnit.SECONDS).build()).build();
        }
        return retrofit;
    }
}
