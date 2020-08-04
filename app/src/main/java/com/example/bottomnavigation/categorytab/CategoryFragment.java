package com.example.bottomnavigation.categorytab;

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
import com.example.bottomnavigation.categorytab.di.CategoryTabModule;
import com.example.bottomnavigation.data.datasource.CategoryRemoteDataSource;
import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.di.ApiBuilderModule;
import com.example.bottomnavigation.utils.ApiBuilder;

import java.util.List;

import retrofit2.Retrofit;

public class CategoryFragment extends Fragment {
    private static final String TAG = "CategoryFragment";
    private TextView pull_Down;
    private ImageView arrow;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private CategoryViewModel categoryViewModel;
    private CategoryViewModelFactory categoryViewModelFactory;
    private Retrofit retrofit = CustomApp.getInstance().getAppModule().provideRetrofit();
    private ApiBuilder builder = ApiBuilderModule.provideApiBuilder(retrofit);
    private ApiService apiService = ApiBuilderModule.provideApiService(builder);
    private CategoryRemoteDataSource categoryRemoteDataSource = CategoryTabModule.provideCategorySource(apiService);


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.category_fragment, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryViewModelFactory = new CategoryViewModelFactory(categoryRemoteDataSource);
        categoryViewModel = new ViewModelProvider(this, categoryViewModelFactory).get(CategoryViewModel.class);

        Log.d(TAG, "onViewCreated: ");


        pull_Down = view.findViewById(R.id.pull_down);
        arrow = view.findViewById(R.id.cat_arrow);
        swipeRefreshLayout = view.findViewById(R.id.refreshing);
        recyclerView = view.findViewById(R.id.recycler_view);


        pull_Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryViewModel();
                Log.d(TAG, "onClick: ");
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryViewModel.getCategoryData();
                Log.d(TAG, "onRefresh: ");
            }
        });

        categoryViewModel();
    }

    @SuppressLint("FragmentLiveDataObserve")

    public void categoryViewModel() {

        pull_Down.setVisibility(View.GONE);
        arrow.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(true);

        categoryViewModel.loadingLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean loadingState) {
                if (loadingState) {
                    pull_Down.setVisibility(View.GONE);
                    arrow.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(true);
                    Log.d(TAG, "loadingOnChanged: ");
                } else {
                    pull_Down.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    Log.d(TAG, "onChanged:loading false");
                }

            }
        });

        categoryViewModel.errorStateLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hasError) {
                if (hasError) {
                    pull_Down.setVisibility(View.VISIBLE);
                    arrow.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getContext(), "Check Your Conecction !", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onChanged: Error");
                } else {
                    pull_Down.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);

                }
            }
        });

        categoryViewModel.categoryLiveData.observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categoryList) {
                showData(categoryList);
            }
        });


    }

    public void showData(List<Category> categories) {

        Log.d(TAG, "showData: " + categories.toString());

        CategoryAdapter adapter = new CategoryAdapter(categories, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
