package com.example.bottomnavigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends Fragment{
    NavController navController;
    BottomNavigationView navigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navigationView = view.findViewById(R.id.navigation);
        navController = Navigation.findNavController(view);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.more:
                        navController.navigate(R.id.action_mainFragment_to_moreFragment);
                        break;
                    case R.id.grouping:
                        navController.navigate(R.id.action_mainFragment_to_groupingFragment);
                        break;
                    case R.id.home:
                        navController.navigate(R.id.action_mainFragment_to_homeFragment);
                        break;
                }
                return true;
            }
        });
    }


}