package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Store;

public interface CallBackListener {

    void onResponse(Store store);
    void onFailure(Throwable throwable);
}
