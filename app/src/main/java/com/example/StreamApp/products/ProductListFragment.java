package com.example.StreamApp.products;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.StreamApp.ApiService;
import com.example.StreamApp.CustomApp;
import com.example.StreamApp.R;
import com.example.StreamApp.data.datasource.remote.ProductListRemoteDataSource;
import com.example.StreamApp.data.model.Product;
import com.example.StreamApp.di.ApiBuilderModule;
import com.example.StreamApp.productdetail.ProductListener;
import com.example.StreamApp.products.di.ProductModule;
import com.example.StreamApp.utils.ApiBuilder;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

import retrofit2.Retrofit;

public class ProductListFragment extends Fragment {
    private TextView refresh;
    private ImageView arrow;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View progressBar;
    private RecyclerView recyclerView;
    private NavController navController;
    private ProductListViewModel productListViewModel;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(apiBuilder);
    private ProductListRemoteDataSource productListRemoteDataSource = ProductModule.provideProductListRemoteDataSource(apiService);
    private ProductListViewModelFactory productListViewModelFactory = ProductModule.provideProductListViewModelFactory(productListRemoteDataSource);
    private ProductListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productListViewModel = new ViewModelProvider(this, productListViewModelFactory).get(ProductListViewModel.class);

        int categoryId = getArguments().getInt("categoryId");
        String categoryTitle = getArguments().getString("categoryTitle");
        refresh = view.findViewById(R.id.refresh);
        arrow = view.findViewById(R.id.productArrow);
        swipeRefreshLayout = view.findViewById(R.id.productRefreshing);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        MaterialToolbar toolbar = view.findViewById(R.id.toolbar);
        navController = Navigation.findNavController(view);
        toolbar.setTitle(categoryTitle);

        observeProductListViewModel();
        showProductList();
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productListViewModel.loadData();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                productListViewModel.loadData();
            }
        });
        productListViewModel.setCategoryId(categoryId);
        productListViewModel.getFirstData();
    }

    public void observeProductListViewModel() {
        refresh.setVisibility(View.GONE);
        arrow.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);
        progressBar.setVisibility(View.GONE);
        productListViewModel.loadingLiveData.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loadingState) {
                if (loadingState) {
                    refresh.setVisibility(View.GONE);
                    arrow.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    refresh.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        productListViewModel.errorStateLiveData.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hasError) {
                if (hasError) {
                    refresh.setVisibility(View.VISIBLE);
                    arrow.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getContext(), "Check Your Connection !", Toast.LENGTH_SHORT).show();
                } else {
                    refresh.setVisibility(View.GONE);
                    arrow.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

        productListViewModel.productListLiveData.observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productLists) {
                if (!productLists.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    adapter.addList(productLists);
                    adapter.notifyDataSetChanged();
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void showProductList() {
        adapter = new ProductListAdapter(getContext(), new ProductListener() {
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt("productId", id);
                navController.navigate(R.id.action_productListFragment_to_productDetailFragment, bundle);
            }
        });
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
                productListViewModel.loadData();
            }
        }
    };
}
