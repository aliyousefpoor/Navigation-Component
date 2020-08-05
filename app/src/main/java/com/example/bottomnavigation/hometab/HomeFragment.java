package com.example.bottomnavigation.hometab;

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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.bottomnavigation.ApiService;
import com.example.bottomnavigation.CustomApp;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.remote.HomeRemoteDataSource;
import com.example.bottomnavigation.data.model.Product;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.hometab.di.HomeTabModule;
import com.example.bottomnavigation.hometab.homeadapter.MultipleTypeAdapter;
import com.example.bottomnavigation.data.model.Homeitem;
import com.example.bottomnavigation.data.model.Store;
import com.example.bottomnavigation.utils.ApiBuilder;

import java.util.List;

import retrofit2.Retrofit;

@SuppressLint("FragmentLiveDataObserve")

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";


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
                showData(store);
            }
        });
    }

    private void showData(Store response) {
        Log.d(TAG, "viewPagerAdapter: " + response.getHomeitem());

        List<Homeitem> homeList = response.getHomeitem();
        List<Product> headerList = response.getHeaderitem();

        MultipleTypeAdapter adapter = new MultipleTypeAdapter(getContext(), homeList, headerList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

}
