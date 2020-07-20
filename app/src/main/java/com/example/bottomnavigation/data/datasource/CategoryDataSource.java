package com.example.bottomnavigation.data.datasource;

import com.example.bottomnavigation.data.model.Category;

import java.util.List;

public interface CategoryDataSource {
    void getCategory(DataSourceListener<List<Category>> dataSourceListener);
}
