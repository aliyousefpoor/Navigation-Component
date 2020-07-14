package com.example.bottomnavigation.homeTab.h_adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.homeTab.h_model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Product> productList;

    public ProductAdapter(List<Product> productList ,Context context){
        this.context=context;
        this.productList=productList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(context).inflate(R.layout.product_layout,parent,false);
        return new ProductViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ProductViewHolder pro_holder = (ProductViewHolder) holder;

        pro_holder.textView.setText(productList.get(position).getName());
        Uri uri = Uri.parse("https://api.vasapi.click/"+productList.get(position).getFeatureAvatar().getXxxdpi());
        Glide.with(context).load(uri).into(pro_holder.imageView);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textView= itemView.findViewById(R.id.discription);
            imageView =itemView.findViewById(R.id.cv_image);

        }
    }
}
