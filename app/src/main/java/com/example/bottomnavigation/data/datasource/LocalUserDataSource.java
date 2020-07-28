package com.example.bottomnavigation.data.datasource;

import android.content.Context;


public interface LocalUserDataSource {

    void saveInformation(int userId, String token , String name , String date , String gender , Context context);
}
