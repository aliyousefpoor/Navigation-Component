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
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.model.Category;

import java.util.List;

public class CategoryFragment extends Fragment {
    private static final String TAG = "CategoryFragment";
    TextView pull_Down;
    ImageView arrow;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    CategoryViewModel categoryViewModel;
    NavController navController;


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
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

        Log.d(TAG, "onViewCreated: ");

        navController = Navigation.findNavController(view);
        pull_Down = view.findViewById(R.id.pull_down);
        arrow = view.findViewById(R.id.cat_arrow);
        swipeRefreshLayout = view.findViewById(R.id.refreshing);
        recyclerView = view.findViewById(R.id.recycler_view);


        pull_Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryViewMethod();
                Log.d(TAG, "onClick: ");
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryViewModel.getCategoryData();
            }
        });

        categoryViewMethod();
    }

    @SuppressLint("FragmentLiveDataObserve")
    public void categoryViewMethod() {

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
                }
                else {
                    pull_Down.setVisibility(View.GONE);
                    arrow.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                    Log.d(TAG, "onChanged: else");
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

    public void showData(List<Category> category) {
        List<Category> categoryList = category;
        Log.d(TAG, "showData: " + categoryList.toString());
        CategoryAdapter adapter = new CategoryAdapter(categoryList, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
