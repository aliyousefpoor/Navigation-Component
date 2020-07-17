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

import org.jetbrains.annotations.NotNull;

import java.util.List;



public class ViewPagerAdapter extends PagerAdapter {
    private static final String TAG = "ViewPagerAdapter";
    Integer id;
    private Context context;
    private List<Product> list;
     ImageView imageView;


    public ViewPagerAdapter(List<Product> list , Context context) {

        this.list = list;
        this.context=context;

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
        id = list.get(position).getId();
        imageView = view.findViewById(R.id.view_pager);


        Uri uri = Uri.parse("https://api.vasapi.click/" + list.get(position).getFeatureAvatar().getXxxdpi());

        Glide.with(context).load(uri).into(imageView);



        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }

}
