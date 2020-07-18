package com.example.bottomnavigation.data.repository;


import android.util.Log;


import com.example.bottomnavigation.RemoteDataListener;
import com.example.bottomnavigation.RepositoryListener;
import com.example.bottomnavigation.data.datasource.RemoteDataSource;
import com.example.bottomnavigation.data.model.Store;

public class DataRepository {
    private static final String TAG = "DataRepository";
    private RepositoryListener repositoryListener;
    RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();

    private static final DataRepository repoInstance = new DataRepository();


    public static DataRepository getInstance() {
        return repoInstance;
    }


    public void callBack(RepositoryListener repositoryListener) {
        this.repositoryListener = repositoryListener;

    }


    public void getCallback() {

        remoteDataSource.remoteCallBack(new RemoteDataListener() {
            @Override
            public void onResponse(Store store) {
                repositoryListener.onResponse(store);
            }

            @Override
            public void onFailure(Throwable throwable) {
                repositoryListener.onFailure(throwable);
            }
        });

        remoteDataSource.getStore();

    }
}
