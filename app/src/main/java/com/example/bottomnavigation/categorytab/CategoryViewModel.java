package com.example.bottomnavigation.categorytab;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.RepositoryCategoryListener;
import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.data.repository.DataRepository;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    private static final String TAG = "CategoryViewModel";
    DataRepository dataRepository = DataRepository.getInstance();

    public CategoryViewModel(){
        getCategoryData();
    }

    private MutableLiveData<List<Category>> _categoryListLiveData = new MutableLiveData<>();
    public LiveData<List<Category>> categoryLiveData = _categoryListLiveData;

    private MutableLiveData<Boolean> _loadingLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadingLiveData = _loadingLiveData;

    private MutableLiveData<Boolean> _errorStateLiveData = new MutableLiveData<>();
    public LiveData<Boolean> errorStateLiveData = _errorStateLiveData;


    public void getCategoryData(){
        Log.d(TAG, "getCategoryData:1 ");
        _loadingLiveData.setValue(true);

        dataRepository.repositoryCategoryCallBack(new RepositoryCategoryListener() {
            @Override
            public void onCategoryResponse(List<Category> category) {
                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(false);
                _categoryListLiveData.setValue(category);
                Log.d(TAG, "onCategoryResponse: " +category.toString());
            }

            @Override
            public void onCategoryFailure(Throwable throwable) {
                _errorStateLiveData.setValue(true);
                _loadingLiveData.setValue(false);
            }
        });

        dataRepository.getCategoryCallBack();
    }
}
