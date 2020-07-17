package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Store;

public interface ResponseListener {

    void onResponse(Store store);
    void onFailure(Throwable throwable);
}
