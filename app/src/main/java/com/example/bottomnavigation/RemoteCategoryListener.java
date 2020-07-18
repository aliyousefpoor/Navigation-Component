package com.example.bottomnavigation;

import com.example.bottomnavigation.data.model.Category;

import java.util.List;

public interface RemoteCategoryListener {
    void onCategoryResponse(List<Category> category);
    void onCategoryFailure(Throwable throwable);
}
