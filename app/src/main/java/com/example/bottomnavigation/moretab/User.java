package com.example.bottomnavigation.moretab;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name ="date")
    private double date;
    @ColumnInfo(name = "gender")
    private String gender;

    public User(int id ,String name , double date ,String gender){
        this.id=id;
        this.name=name;
        this.date=date;
        this.gender=gender;
    }
    @Ignore
    public User(String name,double date, String gender){
        this.name=name;
        this.date=date;
        this.gender=gender;
    }
}
