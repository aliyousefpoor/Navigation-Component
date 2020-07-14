package com.example.bottomnavigation;

import android.view.View;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Store {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("parent_categories")
    @Expose
    private List<ParentCategory> parentCategories = null;
    @SerializedName("tabStrip")
    @Expose
    private List<Object> tabStrip = null;
    @SerializedName("headeritem")
    @Expose
    private List<Headeritem> headeritem = null;
    @SerializedName("homeitem")
    @Expose
    private List<Homeitem> homeitem = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ParentCategory> getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(List<ParentCategory> parentCategories) {
        this.parentCategories = parentCategories;
    }

    public List<Object> getTabStrip() {
        return tabStrip;
    }

    public void setTabStrip(List<Object> tabStrip) {
        this.tabStrip = tabStrip;
    }

    public List<Headeritem> getHeaderitem() {
        return headeritem;
    }

    public void setHeaderitem(List<Headeritem> headeritem) {
        this.headeritem = headeritem;
    }

    public List<Homeitem> getHomeitem() {
        return homeitem;
    }

    public void setHomeitem(List<Homeitem> homeitem) {
        this.homeitem = homeitem;
    }
}
