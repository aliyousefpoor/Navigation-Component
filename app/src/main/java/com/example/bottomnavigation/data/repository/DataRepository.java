package com.example.bottomnavigation.data.repository;


import android.util.Log;

import com.example.bottomnavigation.RemoteCategoryListener;
import com.example.bottomnavigation.RemoteStoreListener;
import com.example.bottomnavigation.RepositoryCategoryListener;
import com.example.bottomnavigation.RepositoryStoreListener;
import com.example.bottomnavigation.data.datasource.RemoteDataSource;
import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.data.model.Store;

import java.util.List;

public class DataRepository {
    private static final String TAG = "DataRepository";
    private RepositoryStoreListener repositoryStoreListener;
    private RepositoryCategoryListener repositoryCategoryListener;
    RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();

    private static final DataRepository repoInstance = new DataRepository();


    public static DataRepository getInstance() {
        return repoInstance;
    }


    public void repositoryStoreCallBack(RepositoryStoreListener repositoryStoreListener) {
        this.repositoryStoreListener = repositoryStoreListener;

    }

    public void repositoryCategoryCallBack(RepositoryCategoryListener repositoryCategoryListener){
        this.repositoryCategoryListener = repositoryCategoryListener;
    }


    public void getStoreCallback() {

        remoteDataSource.remoteStoreCallBack(new RemoteStoreListener() {
            @Override
            public void onStoreResponse(Store store) {
                repositoryStoreListener.onStoreResponse(store);

            }

            @Override
            public void onStoreFailure(Throwable throwable) {
                repositoryStoreListener.onStoreFailure(throwable);
            }


        });

        remoteDataSource.getStore();

    }


    public void getCategoryCallBack(){
        remoteDataSource.remoteCategoryCallBack(new RemoteCategoryListener() {
            @Override
            public void onCategoryResponse(List<Category> category) {
                repositoryCategoryListener.onCategoryResponse(category);
                Log.d(TAG, "onCategoryResponse: " + category.toString());
            }

            @Override
            public void onCategoryFailure(Throwable throwable) {
                repositoryCategoryListener.onCategoryFailure(throwable);

            }
        });
        remoteDataSource.getCategory();
    }


}
