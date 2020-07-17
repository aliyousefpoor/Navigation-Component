package com.example.bottomnavigation;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.model.Store;

public class AppViewModel extends ViewModel {
    private static final String TAG = "AppViewModel";
    DataRepository dataRepository = DataRepository.getInstance();


    public AppViewModel() {
        getData();
    }


    //nokte
    private MutableLiveData<Store> _storeListLivedata = new MutableLiveData<>();
    public LiveData<Store> storeListLivedata = _storeListLivedata;

    private MutableLiveData<Boolean> _loadingLiveData = new MutableLiveData<>();

    public LiveData<Boolean> loadingLiveData = _loadingLiveData;

    private MutableLiveData<Boolean> _errorStateLiveData = new MutableLiveData<>();

    public LiveData<Boolean> errorStateLiveData = _errorStateLiveData;


    public void getData() {
        Log.d(TAG, "getData: ");
        _loadingLiveData.setValue(true);

//        CallBackListener callBack = new CallBackListener() {
//            @Override
//            public void onResponse(Store store) {
//                _loadingLiveData.setValue(false);
//                _errorStateLiveData.setValue(false);
//                _storeListLivedata.setValue(store);
//
//            }
//
//            @Override
//            public void onFailure(Throwable throwable) {
//                _loadingLiveData.setValue(false);
//                _errorStateLiveData.setValue(true);
//            }
//        };


        dataRepository.onCallBackListener(new CallBackListener() {
            @Override
            public void onResponse(Store store) {

                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(false);
                _storeListLivedata.setValue(store);
            }

            @Override
            public void onFailure(Throwable throwable) {

                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(true);
            }
        });

        dataRepository.getStore();

    }


}
