package com.example.bottomnavigation.hometab.homeadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.model.Homeitem;
import com.example.bottomnavigation.data.model.Product;

import java.util.List;

public class MultipleTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "MultipleTypeAdapter";

    private static final int ViewPager_Type = 1;
    private static final int HorizontalList_Type = 2;

    private Context context;
    private List<Homeitem> homeList;
    private List<Product> headerList;


    public MultipleTypeAdapter(Context context, List<Homeitem> homeList, List<Product> headerList) {

        this.context = context;
        this.homeList = homeList;
        this.headerList = headerList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ViewPager_Type;
        } else {
            return HorizontalList_Type;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        RecyclerView.ViewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        switch (viewType) {
            case ViewPager_Type:
                View pager_view = inflater.inflate(R.layout.header_item_layout, parent, false);
                holder = new ViewPagerViewHolder(pager_view);
                break;

            case HorizontalList_Type:
                View list_view = inflater.inflate(R.layout.home_item_layout, parent, false);
                holder = new ListViewHolder(list_view);
                break;
        }

        return holder;
    }


    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        switch (getItemViewType(position)) {

            case ViewPager_Type:
                Log.d(TAG, "ViewPager_Type: " + position);
                final ViewPagerViewHolder pager_holder = (ViewPagerViewHolder) holder;
                pager_holder.viewPager.setAdapter(new ViewPagerAdapter(headerList, context));

                break;

            case HorizontalList_Type:

                final ListViewHolder list_holder = (ListViewHolder) holder;
                Log.d(TAG, "HorizontalList_Type: " + position);

                list_holder.title.setText(homeList.get(position - 1).getTitle());
                list_holder.pro_recyclerView.setAdapter(new ProductAdapter(homeList.get(position - 1).getProducts(), context));
                list_holder.pro_recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                list_holder.pro_recyclerView.setHasFixedSize(true);

                break;
        }

    }

    @Override
    public int getItemCount() {
        return homeList.size() + 1;
    }


    static class ViewPagerViewHolder extends RecyclerView.ViewHolder {

        private ViewPager viewPager;

        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);

            viewPager = itemView.findViewById(R.id.vp_img);
            viewPager.setRotationY(180);

        }

    }

    static class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private RecyclerView pro_recyclerView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pro_recyclerView = itemView.findViewById(R.id.product_rv);


        }


    }
}
