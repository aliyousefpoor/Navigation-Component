package com.example.bottomnavigation;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.List;

public class MultipleTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ViewPager_Type = 1;
    private static final int CardView_Type = 2;

    private Context context;
    private List<Homeitem> homeList;
    public AppViewModel appViewModel;


    public MultipleTypeAdapter(Context context, List<Homeitem> homeList) {

        this.context = context;
        this.homeList = homeList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ViewPager_Type;
        } else {
            return CardView_Type;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        RecyclerView.ViewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        switch (viewType) {
            case ViewPager_Type:
                View pagerview = inflater.inflate(R.layout.header_layout, parent, false);
                holder = new ViewPagerVH(pagerview);
                break;
            case CardView_Type:
                View listview = inflater.inflate(R.layout.home_item, parent, false);
                holder = new ListVH(listview);
                break;
        }


        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {

            case ViewPager_Type:
                final ViewPagerVH vholder = (ViewPagerVH) holder;
                Uri uri = Uri.parse("https://api.vasapi.click/" + homeList.get(position).getProducts().get(position).getFeatureAvatar().getXxxdpi());
                Glide.with(context).load(uri).into(vholder.imageView);


                break;

            case CardView_Type:

                final ListVH lholder = (ListVH) holder;

                lholder.pro_recyclerView.setAdapter(new ProductAdapter(homeList.get(position).getProducts(), context));
                lholder.pro_recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                lholder.pro_recyclerView.setHasFixedSize(true);
                lholder.title.setText(homeList.get(position).getTitle());


        }


    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }


    static class ViewPagerVH extends RecyclerView.ViewHolder {


        private ImageView imageView;

        public ViewPagerVH(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.vp_img);


        }


    }

    static class ListVH extends RecyclerView.ViewHolder {

        private TextView title;

        private RecyclerView pro_recyclerView;


        public ListVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pro_recyclerView = itemView.findViewById(R.id.product_rv);

        }

    }
}
