package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Store;


public interface RemoteStoreListener {
    void onStoreResponse(Store store);
    void onStoreFailure(Throwable throwable);

}
