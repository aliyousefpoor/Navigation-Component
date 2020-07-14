package com.example.bottomnavigation;

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
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressLint("FragmentLiveDataObserve")

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    ViewPager viewPager;
    NavController navController;
    ImageView arrow;
    TextView pulldown;
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
//        viewPager = view.findViewById(R.id.viewpager);
        arrow = view.findViewById(R.id.arrow);
        pulldown = view.findViewById(R.id.pulldown);
        swipeRefreshLayout = view.findViewById(R.id.swiprefreshing);
        recyclerView = view.findViewById(R.id.rec_view);


        Log.d(TAG, "onViewCreated: ");

        pulldown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataMethod();
                Log.d(TAG, "onClick: ");
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                appViewModel.getData();
            }
        });
        getDataMethod();

    }

    public void getDataMethod() {

        pulldown.setVisibility(View.GONE);
        arrow.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);

        appViewModel.getLoadingLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loadingState) {
                if (loadingState) {
                    pulldown.setVisibility(View.GONE);
                    arrow.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(true);
                } else {
                    pulldown.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });

        appViewModel.getErrorStateLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hasError) {
                if (hasError) {
                    pulldown.setVisibility(View.VISIBLE);
                    arrow.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerView.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Check Your Conecction !", Toast.LENGTH_SHORT).show();
                } else {

                }

            }
        });

        appViewModel.getStoreList().observe(this, new Observer<Store>() {
            @Override
            public void onChanged(Store store) {
                viewPagerAdapter(store);
            }
        });


    }

    private void viewPagerAdapter(Store response) {
        Log.d(TAG, "viewPagerAdapter: " + response.getHomeitem());
        List<Homeitem> homeList = response.getHomeitem();

        MultipleTypeAdapter adapter = new MultipleTypeAdapter( getContext(), homeList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

}
