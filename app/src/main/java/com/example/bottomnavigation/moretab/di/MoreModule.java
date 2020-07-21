package com.example.bottomnavigation.moretab.di;


import android.view.View;

import com.example.bottomnavigation.moretab.MoreAdapter;


public class MoreModule {
    public static MoreAdapter.MoreViewHolder provideMoreViewHolder(View view) {
        return new MoreAdapter.MoreViewHolder(view);
    }

}
