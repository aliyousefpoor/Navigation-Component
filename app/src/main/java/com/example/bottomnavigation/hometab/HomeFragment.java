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
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;

import androidx.navigation.Navigation;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.bottomnavigation.AppViewModel;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.model.Product;
import com.example.bottomnavigation.hometab.homeadapter.MultipleTypeAdapter;
import com.example.bottomnavigation.data.model.Homeitem;
import com.example.bottomnavigation.data.model.Store;

import java.util.List;

@SuppressLint("FragmentLiveDataObserve")

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    NavController navController;
    ImageView arrow;
    TextView pullDown;
    SwipeRefreshLayout swipeRefreshLayout;
    AppViewModel appViewModel;
    RecyclerView recyclerView;



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
        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);


        navController = Navigation.findNavController(view);
        arrow = view.findViewById(R.id.arrow);
        pullDown = view.findViewById(R.id.pulldown);
        swipeRefreshLayout = view.findViewById(R.id.swiprefreshing);
        recyclerView = view.findViewById(R.id.rec_view);


        Log.d(TAG, "onViewCreated: ");

        pullDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observeViewMethod();
                Log.d(TAG, "onClick: ");
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                appViewModel.getData();
            }
        });
        observeViewMethod();

    }

    public void observeViewMethod() {

        pullDown.setVisibility(View.GONE);
        arrow.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);

        appViewModel.loadingLiveData.observe(this, new Observer<Boolean>() {
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

        appViewModel.errorStateLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hasError) {
                if (hasError) {
                    pullDown.setVisibility(View.VISIBLE);
                    arrow.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerView.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Check Your Conecction !", Toast.LENGTH_SHORT).show();
                } else {

                }

            }
        });

        appViewModel.storeListLivedata.observe(this, new Observer<Store>() {
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

        MultipleTypeAdapter adapter = new MultipleTypeAdapter( getContext(), homeList,headerList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

}
