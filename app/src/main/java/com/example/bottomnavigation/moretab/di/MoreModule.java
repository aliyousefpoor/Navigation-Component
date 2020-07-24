package com.example.bottomnavigation.moretab.di;



import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.data.datasource.UserSource;
import com.example.bottomnavigation.data.model.User;



public class MoreModule {
 public static UserSource provideUserSource(ApiService apiService ){
     return new UserSource(apiService);
 }

}
