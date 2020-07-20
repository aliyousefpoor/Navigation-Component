package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.data.model.Store;

public interface HomeDataSource {
    void getStore( DataSourceListener<Store> dataSourceListener);
}
