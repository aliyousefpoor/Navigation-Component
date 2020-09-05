package com.example.bottomnavigation.categorytab;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.remote.CategoryRemoteDataSource;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.remote.ProductListRemoteDataSource;
import com.example.bottomnavigation.data.model.Category;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    private static final String TAG = "CategoryViewModel";
    private CategoryRemoteDataSource categoryRemoteDataSource;

    public CategoryViewModel(CategoryRemoteDataSource categoryRemoteDataSource) {
        this.categoryRemoteDataSource = categoryRemoteDataSource;
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

        categoryRemoteDataSource.getCategory(new DataSourceListener<List<Category>>() {
            @Override
            public void onResponse(List<Category> response) {
                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(false);
                _categoryListLiveData.setValue(response);
                Log.d(TAG, "onCategoryResponse: " + response.toString());
            }

            @Override
            public void onFailure(Throwable throwable) {
                _errorStateLiveData.setValue(true);
                _loadingLiveData.setValue(false);

            }
        });

    }

    public void getList(){

    }

}
