package com.example.bottomnavigation.hometab.di;
import com.example.bottomnavigation.data.datasource.HomeSource;


public class HomeTabModule {
    public static HomeSource provideCategorySource(){
        return new HomeSource();
    }
}
