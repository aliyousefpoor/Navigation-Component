package com.example.bottomnavigation.productdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.remote.ProductDetailRemoteDataSource;
import com.example.bottomnavigation.data.model.Comment;
import com.example.bottomnavigation.data.model.ProductsList;

import java.util.List;

public class ProductDetailViewModel extends ViewModel {
    private Integer id;
    private ProductDetailRemoteDataSource productDetailRemoteDataSource;

    public ProductDetailViewModel(ProductDetailRemoteDataSource productDetailRemoteDataSource) {
        this.productDetailRemoteDataSource = productDetailRemoteDataSource;
    }

    private MutableLiveData<ProductsList> _productDetailLiveData = new MutableLiveData<>();
    public LiveData<ProductsList> productDetailLiveData = _productDetailLiveData;

    private MutableLiveData<List<Comment>> _productComment = new MutableLiveData<>();
    public LiveData<List<Comment>> productComment = _productComment;

    private MutableLiveData<Boolean> _loadingLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadingLiveData = _loadingLiveData;

    public void getProductDetails() {
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

    public void getProductComment() {
        productDetailRemoteDataSource.getProductComment(id, new DataSourceListener<List<Comment>>() {
            @Override
            public void onResponse(List<Comment> response) {
                _productComment.setValue(response);
                _loadingLiveData.setValue(false);
            }
            @Override
            public void onFailure(Throwable throwable) {
                _loadingLiveData.setValue(false);
            }
        });
    }

    public void getProductDetail() {
        getProductDetails();
        getProductComment();
    }

    public void setProductId(int id) {
        this.id = id;
    }
}
