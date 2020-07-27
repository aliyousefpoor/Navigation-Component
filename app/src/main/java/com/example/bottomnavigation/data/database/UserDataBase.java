package com.example.bottomnavigation.data.database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bottomnavigation.data.model.User;


@Database(entities = User.class,exportSchema = false,version = 1)
public abstract class UserDataBase extends RoomDatabase {

    private static final String DB_Name = "user_db";
    private static UserDataBase instance;

    public static synchronized UserDataBase getInstance(Context context){
        if (instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),UserDataBase.class,DB_Name)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract UserDao userDao();

}
