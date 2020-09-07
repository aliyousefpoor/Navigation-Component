package com.example.bottomnavigation.productdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bottomnavigation.data.datasource.DataSourceListener;
import com.example.bottomnavigation.data.datasource.local.UserLocaleDataSourceImpl;
import com.example.bottomnavigation.data.datasource.local.database.IsLoginListener;
import com.example.bottomnavigation.data.datasource.remote.ProductDetailRemoteDataSource;
import com.example.bottomnavigation.data.model.Comment;
import com.example.bottomnavigation.data.model.Product;
import com.example.bottomnavigation.moretab.SingleLiveEvent;

import java.util.List;

public class ProductDetailViewModel extends ViewModel {
    private Integer id;
    private ProductDetailRemoteDataSource productDetailRemoteDataSource;
    private UserLocaleDataSourceImpl userLocaleDataSource;

    public ProductDetailViewModel(ProductDetailRemoteDataSource productDetailRemoteDataSource,UserLocaleDataSourceImpl userLocaleDataSource) {
        this.productDetailRemoteDataSource = productDetailRemoteDataSource;
        this.userLocaleDataSource=userLocaleDataSource;
    }

    private MutableLiveData<Product> _productDetailLiveData = new MutableLiveData<>();
    public LiveData<Product> productDetailLiveData = _productDetailLiveData;

    private MutableLiveData<List<Comment>> _productComment = new MutableLiveData<>();
    public LiveData<List<Comment>> productComment = _productComment;

    private MutableLiveData<Boolean> _loadingLiveData = new MutableLiveData<>();
    public LiveData<Boolean> loadingLiveData = _loadingLiveData;

    private SingleLiveEvent<Boolean> _isLogin = new SingleLiveEvent<>();
    public SingleLiveEvent<Boolean> isLogin = _isLogin;

    public void getProductDetails() {
        _loadingLiveData.setValue(true);
        productDetailRemoteDataSource.getProductDetail(id, new DataSourceListener<Product>() {
            @Override
            public void onResponse(Product response) {
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
    public void isLogin(){
        userLocaleDataSource.isLogin(new IsLoginListener() {
            @Override
            public void isLogin(Boolean isLogin) {
                if (isLogin){
                    _isLogin.postValue(true);
                }
                else {
                    _isLogin.postValue(false);
                }
            }
        });
    }
}
