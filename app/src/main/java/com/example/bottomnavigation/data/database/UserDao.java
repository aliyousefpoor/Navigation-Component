package com.example.bottomnavigation.data.database;

import androidx.room.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bottomnavigation.data.model.User;

import java.util.List;


@Dao
public interface UserDao {

    @Query("SELECT * FROM user ORDER BY userId")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE EXISTS (SELECT userId FROM user)")
    User getUser();

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

}
