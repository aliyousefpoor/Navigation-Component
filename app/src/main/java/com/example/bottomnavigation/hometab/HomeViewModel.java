package com.example.bottomnavigation.hometab;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.RepositoryStoreListener;
import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.data.model.Store;
import com.example.bottomnavigation.data.repository.DataRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private static final String TAG = "AppViewModel";
    DataRepository dataRepository = DataRepository.getInstance();


    public HomeViewModel() {
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

        dataRepository.repositoryStoreCallBack(new RepositoryStoreListener() {
            @Override
            public void onStoreResponse(Store store) {
                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(false);
                _storeListLiveData.setValue(store);
            }

            @Override
            public void onStoreFailure(Throwable throwable) {
                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(true);
            }


        });

        dataRepository.getStoreCallback();

    }


}
