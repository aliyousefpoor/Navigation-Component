package com.example.bottomnavigation.categorytab.di;

import com.example.bottomnavigation.data.datasource.CategorySource;

public class CategoryTabModule {
    public static CategorySource provideCategorySource(){
        return new CategorySource();
    }
}
