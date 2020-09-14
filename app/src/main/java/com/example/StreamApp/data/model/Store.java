package com.example.StreamApp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Store {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("parent_categories")
    @Expose
    private List<Category> parentCategories = null;
    @SerializedName("category")
    @Expose
    private List<Category> category = null;
    @SerializedName("tabStrip")
    @Expose
    private List<Object> tabStrip = null;
    @SerializedName("headeritem")
    @Expose
    private List<Product> headeritem = null;
    @SerializedName("homeitem")
    @Expose
    private List<Homeitem> homeitem = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Category> getParentCategories() {
        return parentCategories;
    }

    public void setParentCategories(List<Category> parentCategories) {
        this.parentCategories = parentCategories;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Object> getTabStrip() {
        return tabStrip;
    }

    public void setTabStrip(List<Object> tabStrip) {
        this.tabStrip = tabStrip;
    }

    public List<Product> getHeaderitem() {
        return headeritem;
    }

    public void setHeaderitem(List<Product> headeritem) {
        this.headeritem = headeritem;
    }

    public List<Homeitem> getHomeitem() {
        return homeitem;
    }

    public void setHomeitem(List<Homeitem> homeitem) {
        this.homeitem = homeitem;
    }
}
