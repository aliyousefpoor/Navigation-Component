package com.example.bottomnavigation.categorytab;

import android.content.Context;
import android.net.Uri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.model.Category;
import com.example.bottomnavigation.utils.AppConstants;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Category> categoryList;

    public CategoryAdapter (List<Category> categoryList,Context context){
        this.context=context;
        this.categoryList=categoryList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;
       view = LayoutInflater.from(context).inflate(R.layout.category_adapter_layout,parent,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final CategoryViewHolder categoryViewHolder =(CategoryViewHolder) holder;

        categoryViewHolder.textView.setText(categoryList.get(position).getTitle());
        Uri uri = Uri.parse(AppConstants.baseUrl+categoryList.get(position).getAvatar());
        Glide.with(context).load(uri).into(categoryViewHolder.imageView);

        categoryViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,categoryList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        private CardView cardView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.title);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

}
