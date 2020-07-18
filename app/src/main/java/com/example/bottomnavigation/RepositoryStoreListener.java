package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.data.model.Store;

public interface RepositoryStoreListener {

    void onStoreResponse(Store store);
    void onStoreFailure(Throwable throwable);

}
