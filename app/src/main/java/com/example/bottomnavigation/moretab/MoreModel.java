package com.example.bottomnavigation.moretab;


public class MoreModel {


    public String title;

    public enum Type {
        Profile, About, Contact
    }

    public Type type;


    public MoreModel(String title, Type type) {
        this.title = title;
        this.type = type;

    }
}
