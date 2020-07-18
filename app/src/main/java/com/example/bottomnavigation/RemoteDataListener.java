package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Store;

public interface RemoteDataListener {
    void onResponse(Store store);
    void onFailure(Throwable throwable);
}
