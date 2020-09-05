package com.example.bottomnavigation.productdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.remote.ProductDetailRemoteDataSource;
import com.example.bottomnavigation.data.model.ProductsList;

public class ProductDetailViewModel extends ViewModel {
    private ProductDetailRemoteDataSource productDetailRemoteDataSource;

    public ProductDetailViewModel(ProductDetailRemoteDataSource productDetailRemoteDataSource) {
        this.productDetailRemoteDataSource = productDetailRemoteDataSource;
    }

    private MutableLiveData<ProductsList> _productDetailLiveData = new MutableLiveData<>();
    public LiveData<ProductsList> productDetailLiveData =_productDetailLiveData;

    private MutableLiveData<Boolean> _loadingLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadingLiveData = _loadingLiveData;

    public void getProductDetails(int id){
        _loadingLiveData.setValue(true);
        productDetailRemoteDataSource.getProductDetail(id, new DataSourceListener<ProductsList>() {
            @Override
            public void onResponse(ProductsList response) {
                _productDetailLiveData.setValue(response);
                _loadingLiveData.setValue(false);
            }

            @Override
            public void onFailure(Throwable throwable) {
                _loadingLiveData.setValue(false);
            }
        });
    }
}
