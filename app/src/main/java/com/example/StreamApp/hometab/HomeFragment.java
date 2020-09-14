package com.example.StreamApp.hometab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.StreamApp.ApiService;
import com.example.StreamApp.CustomApp;
import com.example.StreamApp.R;
import com.example.StreamApp.data.datasource.remote.HomeRemoteDataSource;
import com.example.StreamApp.data.model.Product;
import com.example.StreamApp.di.ApiBuilderModule;
import com.example.StreamApp.hometab.di.HomeTabModule;
import com.example.StreamApp.hometab.homeadapter.MultipleTypeAdapter;
import com.example.StreamApp.data.model.Homeitem;
import com.example.StreamApp.data.model.Store;
import com.example.StreamApp.productdetail.ProductListener;
import com.example.StreamApp.utils.ApiBuilder;

import java.util.List;

import retrofit2.Retrofit;

@SuppressLint("FragmentLiveDataObserve")

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    private NavController navController;
    private ImageView arrow;
    private TextView pullDown;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private HomeRemoteDataSource homeRemoteDataSource = HomeTabModule.provideHomeSource(apiService);
    private HomeViewModelFactory homeViewModelFactory = HomeTabModule.provideHomeViewModelFactory(homeRemoteDataSource);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        Log.d(TAG, "onCreateView: ");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel.class);


        arrow = view.findViewById(R.id.arrow);
        pullDown = view.findViewById(R.id.pull_down);
        swipeRefreshLayout = view.findViewById(R.id.swipRefreshing);
        recyclerView = view.findViewById(R.id.rec_view);
        navController = Navigation.findNavController(view);

        Log.d(TAG, "onViewCreated: ");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeViewModel.getStoreData();
                Log.d(TAG, "onRefresh() called");
            }
        });

        observeViewModel();

    }

    public void observeViewModel() {

        pullDown.setVisibility(View.GONE);
        arrow.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);

        homeViewModel.loadingLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loadingState) {
                if (loadingState) {
                    pullDown.setVisibility(View.GONE);
                    arrow.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(true);
                } else {
                    pullDown.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

        homeViewModel.errorStateLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hasError) {
                if (hasError) {
                    pullDown.setVisibility(View.VISIBLE);
                    arrow.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerView.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Check Your Conecction !", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onChanged: Error");
                }

            }
        });

        homeViewModel.storeListLiveData.observe(this, new Observer<Store>() {
            @Override
            public void onChanged(Store store) {
                showStoreData(store);
            }
        });
    }

    private void showStoreData(Store response) {
        Log.d(TAG, "viewPagerAdapter: " + response.getHomeitem());

        List<Homeitem> homeList = response.getHomeitem();
        List<Product> headerList = response.getHeaderitem();

        MultipleTypeAdapter adapter = new MultipleTypeAdapter(getContext(), homeList, headerList, new ProductListener() {
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt("productId", id);
                navController.navigate(R.id.action_homeFragment_to_productDetailFragment,bundle);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

}
