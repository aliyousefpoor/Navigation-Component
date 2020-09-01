package com.example.bottomnavigation.categorytab.products;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.remote.ProductListRemoteDataSource;
import com.example.bottomnavigation.data.model.ProductsList;

import java.util.List;

public class ProductListViewModel extends ViewModel {
    private ProductListRemoteDataSource productListRemoteDataSource;


    public ProductListViewModel(ProductListRemoteDataSource productListRemoteDataSource) {
        this.productListRemoteDataSource = productListRemoteDataSource;

    }

    private MutableLiveData<List<ProductsList>> _productListLiveData = new MutableLiveData<>();
    public LiveData<List<ProductsList>> productListLiveData = _productListLiveData;

    private MutableLiveData<Boolean> _loadingLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadingLiveData = _loadingLiveData;

    private MutableLiveData<Boolean> _errorStateLiveData = new MutableLiveData<>();
    public LiveData<Boolean> errorStateLiveData = _errorStateLiveData;

    public void getProductList(int id,int offset) {
        _loadingLiveData.setValue(true);
        productListRemoteDataSource.getProductList(id,offset,new DataSourceListener<List<ProductsList>>() {
            @Override
            public void onResponse(List<ProductsList> response) {
                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(false);
                _productListLiveData.setValue(response);
            }

            @Override
            public void onFailure(Throwable throwable) {
                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(true);
                _productListLiveData.setValue(null);
            }
        });
    }
}
