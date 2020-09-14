package com.example.StreamApp.data.datasource.local.database;

import androidx.room.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.StreamApp.data.datasource.local.model.UserEntity;


@Dao
public interface UserDao {

    @Query("SELECT * FROM user ORDER BY userId")
    UserEntity getAll();

    //Todo check
    @Query("SELECT * FROM user WHERE EXISTS (SELECT userId FROM user)")
    UserEntity getUser();

    @Insert
    void insertUser(UserEntity user);

    @Update
    void updateUser(UserEntity user);

    @Query("UPDATE user SET name=:nickName,date=:date,gender=:gender WHERE token=:token")
    void updateProfile(String nickName,String token,String date,String gender);

    @Delete
    void deleteUser(UserEntity user);

}
