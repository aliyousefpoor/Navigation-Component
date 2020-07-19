package com.example.bottomnavigation.categorytab;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.CategorySource;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.Category;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    private static final String TAG = "CategoryViewModel";
    private CategorySource categorySource;

    public CategoryViewModel(CategorySource categorySource) {
        this.categorySource = categorySource;
        getCategoryData();
    }

    private MutableLiveData<List<Category>> _categoryListLiveData = new MutableLiveData<>();
    public LiveData<List<Category>> categoryLiveData = _categoryListLiveData;

    private MutableLiveData<Boolean> _loadingLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadingLiveData = _loadingLiveData;

    private MutableLiveData<Boolean> _errorStateLiveData = new MutableLiveData<>();
    public LiveData<Boolean> errorStateLiveData = _errorStateLiveData;


    public void getCategoryData() {
        Log.d(TAG, "getCategoryData: ");
        _loadingLiveData.setValue(true);

        categorySource.categoryCallBack(new DataSourceListener<List<Category>>() {
            @Override
            public void onResponse(List<Category> category) {

                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(false);
                _categoryListLiveData.setValue(category);
                Log.d(TAG, "onCategoryResponse: " + category.toString());

            }

            @Override
            public void onFailure(Throwable throwable) {
                _errorStateLiveData.setValue(true);
                _loadingLiveData.setValue(false);

            }
        });

        categorySource.getCategory();
    }
}
