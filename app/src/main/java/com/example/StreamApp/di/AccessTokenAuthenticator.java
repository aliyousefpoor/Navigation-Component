package com.example.StreamApp.di;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.StreamApp.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.StreamApp.data.datasource.local.database.UserDatabase;
import com.example.StreamApp.login.di.LoginModule;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class AccessTokenAuthenticator implements Authenticator {
    UserDatabase database = LoginModule.provideUserDatabase();
    private final UserLocaleDataSourceImpl userLocaleDataSource = LoginModule.provideUserLocaleDataSource(database.userDao());

    String newAccessToken;
    String accessToken;

    @Nullable
    @Override
    public Request authenticate(Route route, Response response) {

        return null;
    }

    private boolean isRequestWithAccessToken(@NonNull Response response) {
        String header = response.request().header("Authorization");
        return header != null && header.startsWith("Token ");
    }

    @NonNull
    private Request newRequestWithAccessToken(@NonNull Request request, @NonNull String accessToken) {
        return request.newBuilder()
                .header("Authorization", "Token " + accessToken)
                .build();
    }
}
