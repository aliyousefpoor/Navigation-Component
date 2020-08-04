package com.example.bottomnavigation.data.local.database;

import androidx.room.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bottomnavigation.data.local.model.UserEntity;
import com.example.bottomnavigation.data.model.User;

import java.util.List;


@Dao
public interface UserDao {

    @Query("SELECT * FROM user ORDER BY userId")
    UserEntity getAll();

    @Query("SELECT * FROM user WHERE EXISTS (SELECT userId FROM user)")
    UserEntity getUser();

    @Insert
    void insertUser(UserEntity user);

    @Update
    void updateUser(UserEntity user);

    @Delete
    void deleteUser(UserEntity user);

}
