package com.example.bottomnavigation.productdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.CustomApp;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.datasource.remote.ProductDetailRemoteDataSource;
import com.example.bottomnavigation.data.model.ProductsList;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.products.di.ProductModule;
import com.example.bottomnavigation.utils.ApiBuilder;

import retrofit2.Retrofit;

public class ProductDetailFragment extends Fragment {
    private static final String TAG = "ProductDetailFragment";
    private int id;
    private ImageView avatar;
    private TextView productName;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProductDetailViewModel productDetailViewModel;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(apiBuilder);
    private ProductDetailRemoteDataSource productDetailRemoteDataSource = ProductModule.provideProductDetailRemoteDataSource(apiService);
    private ProductDetailViewModelFactory productDetailViewModelFactory = ProductModule.provideProductDetailViewModelFactory(productDetailRemoteDataSource);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_detail_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productDetailViewModel = new ViewModelProvider(this, productDetailViewModelFactory).get(ProductDetailViewModel.class);
        id = getArguments().getInt("productId");
        avatar = view.findViewById(R.id.productAvatar);
        productName = view.findViewById(R.id.productName);
        recyclerView = view.findViewById(R.id.commentRecyclerView);
        swipeRefreshLayout = view.findViewById(R.id.commentRefreshing);

        observeProductDetailViewModel();
        getProductDetail();

    }

    public void observeProductDetailViewModel() {
        productDetailViewModel.loadingLiveData.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });

        productDetailViewModel.productDetailLiveData.observe(getViewLifecycleOwner(), new Observer<ProductsList>() {
            @Override
            public void onChanged(ProductsList productsList) {
                productName.setText(productsList.getName());
                Glide.with(getContext()).load(productsList.getAvatar()).into(avatar);
            }
        });
    }

    public void getProductDetail() {
        productDetailViewModel.getProductDetails(id);
    }
}
