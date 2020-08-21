package com.example.bottomnavigation.data.datasource;

public interface DataSourceListener<T> {

    void onResponse(T response);
    void onFailure(Throwable throwable);
}
