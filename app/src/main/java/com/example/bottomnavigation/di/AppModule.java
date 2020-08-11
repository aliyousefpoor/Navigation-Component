package com.example.bottomnavigation.di;

import com.example.bottomnavigation.utils.AppConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppModule {

    private Retrofit retrofit = null;


    public Retrofit provideRetrofit() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(AppConstants.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()).client(httpClient.connectTimeout(90, TimeUnit.SECONDS).readTimeout(90, TimeUnit.SECONDS) .writeTimeout(90, TimeUnit.SECONDS).build()).build();
        }
        return retrofit;
    }
}
