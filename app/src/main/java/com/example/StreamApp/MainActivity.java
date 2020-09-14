package com.example.StreamApp;


import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    BottomNavigationView navigationView;
    View more_Nav_host;
    View home_Nav_host;
    View category_Nav_host;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation);
        more_Nav_host = findViewById(R.id.nav_host_more_fragment);
        home_Nav_host = findViewById(R.id.nav_host_home_fragment);
        category_Nav_host = findViewById(R.id.nav_host_cat_fragment);

        more_Nav_host.setVisibility(View.GONE);
        category_Nav_host.setVisibility(View.GONE);
        navigationView.setSelectedItemId(R.id.homeFragment);


        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_more_fragment);


        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homeFragment:

                        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_home_fragment);
                        home_Nav_host.setVisibility(View.VISIBLE);
                        more_Nav_host.setVisibility(View.GONE);
                        category_Nav_host.setVisibility(View.GONE);
                        break;

                    case R.id.moreFragment:

                        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_more_fragment);
                        more_Nav_host.setVisibility(View.VISIBLE);
                        category_Nav_host.setVisibility(View.GONE);
                        home_Nav_host.setVisibility(View.GONE);
                        break;

                    case R.id.categoryFragment:
                        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_cat_fragment);
                        category_Nav_host.setVisibility(View.VISIBLE);
                        home_Nav_host.setVisibility(View.GONE);
                        more_Nav_host.setVisibility(View.GONE);
                        break;

                }

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (!navController.navigateUp()) {
            super.onBackPressed();
        }

    }
}