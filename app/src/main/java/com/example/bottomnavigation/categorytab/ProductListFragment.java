package com.example.bottomnavigation.categorytab;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.CustomApp;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.categorytab.di.CategoryTabModule;
import com.example.bottomnavigation.data.datasource.remote.ProductListRemoteDataSource;
import com.example.bottomnavigation.data.model.ProductsList;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.utils.ApiBuilder;

import java.util.List;

import retrofit2.Retrofit;

public class ProductListFragment extends Fragment {
    private TextView refresh;
    private ImageView arrow;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View progressBar;
    private RecyclerView recyclerView;
    private int id;
    private int offset = 0;
    private ProductListViewModel productListViewModel;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder apiBuilder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(apiBuilder);
    private ProductListRemoteDataSource productListRemoteDataSource = CategoryTabModule.provideProductListRemoteDataSource(apiService);
    private ProductListViewModelFactory productListViewModelFactory = CategoryTabModule.provideProductListViewModelFactory(productListRemoteDataSource);
    private GridLayoutManager layoutManager;
    private ProductListAdapter adapter;
    private int lastVisibleItemPosition;

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

        id = getArguments().getInt("categoryId");
        refresh = view.findViewById(R.id.refresh);
        arrow = view.findViewById(R.id.productArrow);
        swipeRefreshLayout = view.findViewById(R.id.productRefreshing);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        observeProductListViewModel();
        showProductList();
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        getData();
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

        productListViewModel.productListLiveData.observe(getViewLifecycleOwner(), new Observer<List<ProductsList>>() {
            @Override
            public void onChanged(List<ProductsList> productLists) {
                if (!productLists.isEmpty()) {
                    progressBar.setVisibility(View.GONE);
                    adapter.addList(productLists);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void showProductList() {

        adapter = new ProductListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        layoutManager = new GridLayoutManager(getContext(), 2);
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

            lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                    .findLastCompletelyVisibleItemPosition();
            if (lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
                offset = offset + recyclerView.getAdapter().getItemCount();
                getData();
            }
        }
    };

    public void getData() {
        productListViewModel.getProductList(id, offset);
    }
}
