package com.example.StreamApp.di;

import androidx.annotation.NonNull;

import com.example.StreamApp.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.StreamApp.data.datasource.local.database.UserDatabase;
import com.example.StreamApp.login.di.LoginModule;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private UserDatabase database = LoginModule.provideUserDatabase();
    private UserLocaleDataSourceImpl userLocaleDataSource = LoginModule.provideUserLocaleDataSource(database.userDao());

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        if (userLocaleDataSource.getTokenBlocking() != null) {
            Request request = newRequestWithAccessToken(chain.request(), userLocaleDataSource.getTokenBlocking());
            return chain.proceed(request);
        } else {
            Request request = newRequestWithAccessToken(chain.request(), null);
            return chain.proceed(request);
        }
    }

    @NonNull
    private Request newRequestWithAccessToken(@NonNull Request request, String accessToken) {
        if (accessToken != null) {
            return request.newBuilder()
                    .header("Authorization", "Token " + accessToken)
                    .build();
        } else {

            return request.newBuilder().build();
        }
    }
}
