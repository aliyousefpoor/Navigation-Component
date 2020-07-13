package com.example.bottomnavigation;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;



public class ViewPagerAdapter extends PagerAdapter {
    private static final String TAG = "ViewPagerAdapter";
    Integer id;
    private Context context;
    private List<Headeritem> list;
    ImageView imageView;
    HomeFragment homeFragment;


    public ViewPagerAdapter(HomeFragment homeFragment, List<Headeritem> list, Context context) {
        this.context = context;
        this.list = list;
        this.homeFragment = homeFragment;
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

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.viewpager_layout, container, false);
        id = list.get(position).getId();
        imageView = view.findViewById(R.id.vp_img);


        Uri uri = Uri.parse("https://api.vasapi.click/" + list.get(position).getFeatureAvatar().getXxxdpi());

        Glide.with(context).load(uri).into(imageView);

        Log.d(TAG, "instantiateItem: " + list.get(position).getName());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Image" + list.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        });

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
