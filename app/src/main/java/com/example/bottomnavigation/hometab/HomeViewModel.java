package com.example.bottomnavigation.hometab;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.HomeSource;
import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.model.Store;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "AppViewModel";
    private HomeSource homeSource;


    public HomeViewModel(HomeSource homeSource) {
        this.homeSource = homeSource;
        getStoreData();
    }


    private MutableLiveData<Store> _storeListLiveData = new MutableLiveData<>();
    public LiveData<Store> storeListLiveData = _storeListLiveData;

    private MutableLiveData<Boolean> _loadingLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadingLiveData = _loadingLiveData;

    private MutableLiveData<Boolean> _errorStateLiveData = new MutableLiveData<>();
    public LiveData<Boolean> errorStateLiveData = _errorStateLiveData;

    public void getStoreData() {
        Log.d(TAG, "getStoreData: ");
        _loadingLiveData.setValue(true);

        homeSource.getStore(new DataSourceListener<Store>() {
            @Override
            public void onResponse(Store response) {
                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(false);
                _storeListLiveData.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(true);
            }
        });

    }


}
