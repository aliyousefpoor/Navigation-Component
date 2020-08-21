package com.example.bottomnavigation.data.datasource.local.database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bottomnavigation.data.datasource.local.model.UserEntity;


@Database(entities = UserEntity.class,exportSchema = false,version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static final String DB_Name = "user_db";
    private static UserDatabase instance;


    public static synchronized UserDatabase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class,DB_Name)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract UserDao userDao();

}
