package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Store;

public interface RepositoryListener {

    void onResponse(Store store);
    void onFailure(Throwable throwable);
}
