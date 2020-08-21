package com.example.bottomnavigation.hometab.homeadapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.model.Product;
import com.example.bottomnavigation.utils.AppConstants;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ViewPagerAdapter extends PagerAdapter {
    private static final String TAG = "ViewPagerAdapter";

    private Context context;
    private List<Product> list;


    public ViewPagerAdapter(List<Product> list, Context context) {

        this.list = list;
        this.context = context;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return view == object;
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, final int position) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_pager_layout, container, false);
        ImageView imageView = view.findViewById(R.id.view_pager);

//        Uri uri = Uri.parse(AppConstants.baseUrl + list.get(position).getFeatureAvatar().getXxxdpi());
        Glide.with(context).load(list.get(position).getFeatureAvatar().getXxxdpi()).into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, list.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });


        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {

        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

}
