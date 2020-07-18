package com.example.bottomnavigation;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.model.Store;
import com.example.bottomnavigation.data.repository.DataRepository;

public class AppViewModel extends ViewModel {
    private static final String TAG = "AppViewModel";
    DataRepository dataRepository = DataRepository.getInstance();


    public AppViewModel() {
        getData();
    }



    private MutableLiveData<Store> _storeListLiveData = new MutableLiveData<>();
    public LiveData<Store> storeListLiveData = _storeListLiveData;

    private MutableLiveData<Boolean> _loadingLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadingLiveData = _loadingLiveData;

    private MutableLiveData<Boolean> _errorStateLiveData = new MutableLiveData<>();
    public LiveData<Boolean> errorStateLiveData = _errorStateLiveData;


    public void getData() {
        Log.d(TAG, "getData: ");
        _loadingLiveData.setValue(true);

        dataRepository.callBack(new RepositoryListener() {
            @Override
            public void onResponse(Store store) {

                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(false);
                _storeListLiveData.setValue(store);
            }

            @Override
            public void onFailure(Throwable throwable) {

                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(true);
            }
        });

        dataRepository.getCallback();

    }
}
