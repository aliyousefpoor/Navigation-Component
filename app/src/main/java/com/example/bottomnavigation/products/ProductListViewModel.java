package com.example.bottomnavigation.products;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.remote.ProductListRemoteDataSource;
import com.example.bottomnavigation.data.model.ProductsList;
import com.example.bottomnavigation.moretab.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

public class ProductListViewModel extends ViewModel {
    private ProductListRemoteDataSource productListRemoteDataSource;
    int offset = 0;
    Integer categoryId;
    private List<ProductsList> productsLists = new ArrayList<>();

    public ProductListViewModel(ProductListRemoteDataSource productListRemoteDataSource) {
        this.productListRemoteDataSource = productListRemoteDataSource;

    }

    private MutableLiveData<List<ProductsList>> _productListLiveData = new SingleLiveEvent<>();
    public LiveData<List<ProductsList>> productListLiveData = _productListLiveData;

    private MutableLiveData<Boolean> _loadingLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadingLiveData = _loadingLiveData;

    private MutableLiveData<Boolean> _errorStateLiveData = new MutableLiveData<>();
    public LiveData<Boolean> errorStateLiveData = _errorStateLiveData;

    public void loadData() {
        _loadingLiveData.setValue(true);
        productListRemoteDataSource.getProductList(this.categoryId, offset, new DataSourceListener<List<ProductsList>>() {
            @Override
            public void onResponse(List<ProductsList> response) {
                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(false);
                productsLists.addAll(response);
                _productListLiveData.setValue(productsLists);
                offset = offset + response.size();
            }

            @Override
            public void onFailure(Throwable throwable) {
                _loadingLiveData.setValue(false);
                _errorStateLiveData.setValue(true);
                _productListLiveData.setValue(null);
            }
        });
    }

    public void getFirstData() {
        if(productsLists.size() == 0){
            loadData();
        }
    }

    public void setCategoryId(int categoryId) {
        this.categoryId=categoryId;
    }
}
