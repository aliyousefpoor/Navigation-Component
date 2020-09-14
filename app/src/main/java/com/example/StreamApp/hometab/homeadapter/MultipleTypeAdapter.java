package com.example.StreamApp.hometab.homeadapter;

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

import com.example.StreamApp.R;
import com.example.StreamApp.data.model.Homeitem;
import com.example.StreamApp.data.model.Product;
import com.example.StreamApp.productdetail.ProductListener;

import java.util.List;

public class MultipleTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "MultipleTypeAdapter";

    private static final int ViewPagerType = 1;
    private static final int HorizontalListType = 2;

    private Context context;
    private List<Homeitem> homeList;
    private List<Product> headerList;
    private ProductListener productListener;

    public MultipleTypeAdapter(Context context, List<Homeitem> homeList, List<Product> headerList,ProductListener productListener) {

        this.context = context;
        this.homeList = homeList;
        this.headerList = headerList;
        this.productListener=productListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ViewPagerType;
        } else {
            return HorizontalListType;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ViewPagerType:
                View pager_view = inflater.inflate(R.layout.header_item_layout, parent, false);
                holder = new ViewPagerViewHolder(pager_view);
                break;

            case HorizontalListType:
                View list_view = inflater.inflate(R.layout.home_item_layout, parent, false);
                holder = new ListViewHolder(list_view);
                break;
        }

        return holder;
    }


    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        switch (getItemViewType(position)) {

            case ViewPagerType:
                final ViewPagerViewHolder pager_holder = (ViewPagerViewHolder) holder;
                Log.d(TAG, "ViewPager_Type: " + position);

                pager_holder.onBind(headerList, context);

                break;

            case HorizontalListType:

                final ListViewHolder list_holder = (ListViewHolder) holder;
                Log.d(TAG, "HorizontalList_Type: " + position);
                list_holder.onBind(homeList.get(position - 1), context,productListener);


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

        public void onBind(List<Product> headerList, Context context) {
            viewPager.setAdapter(new ViewPagerAdapter(headerList, context));
        }
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private RecyclerView product_recyclerView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            product_recyclerView = itemView.findViewById(R.id.product_rv);

        }

        public void onBind(Homeitem homeitem, Context context, ProductListener productListener) {
            title.setText(homeitem.getTitle());
            product_recyclerView.setAdapter(new ProductAdapter(homeitem.getProducts(), context,productListener));
            product_recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            product_recyclerView.setHasFixedSize(true);
        }
    }
}
