package com.example.bottomnavigation;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.app.Activity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    BottomNavigationView navigationView;
    View moreNavhost;
    View homeNavhost;
    View categorynavhost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigation);
        moreNavhost = findViewById(R.id.nav_host_more_fragment);
        homeNavhost = findViewById(R.id.nav_host_home_fragment);
        categorynavhost = findViewById(R.id.nav_host_cat_fragment);

        homeNavhost.setVisibility(View.GONE);
        categorynavhost.setVisibility(View.GONE);
        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_more_fragment);



        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homeFragment:

                        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_home_fragment);
                        homeNavhost.setVisibility(View.VISIBLE);
                        moreNavhost.setVisibility(View.GONE);
                        categorynavhost.setVisibility(View.GONE);
                        break;

                    case R.id.moreFragment:

                        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_more_fragment);
                        moreNavhost.setVisibility(View.VISIBLE);
                        categorynavhost.setVisibility(View.GONE);
                        homeNavhost.setVisibility(View.GONE);
                        break;

                    case R.id.categoryFragment:
                        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_cat_fragment);
                        categorynavhost.setVisibility(View.VISIBLE);
                        homeNavhost.setVisibility(View.GONE);
                        moreNavhost.setVisibility(View.GONE);
                        break;

                }

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(!navController.navigateUp()) {
            super.onBackPressed();
        }

    }
}