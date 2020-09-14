package com.example.StreamApp.categorytab;

import android.content.Context;

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
import com.example.StreamApp.R;
import com.example.StreamApp.data.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Category> categoryList;
    private CategoryIdListener categoryIdListener;

    public CategoryAdapter(List<Category> categoryList, Context context,CategoryIdListener categoryIdListener) {
        this.context = context;
        this.categoryList = categoryList;
        this.categoryIdListener=categoryIdListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.category_adapter_layout, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;

        categoryViewHolder.onBind(categoryList.get(position), context,categoryIdListener);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private CardView cardView;


        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.title);
            cardView = itemView.findViewById(R.id.card_view);
        }

        public void onBind(final Category category, final Context context,final CategoryIdListener categoryIdListener) {
            textView.setText(category.getTitle());
            Glide.with(context).load(category.getAvatar()).into(imageView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, category.getTitle(), Toast.LENGTH_SHORT).show();
                    categoryIdListener.onClick(category.getId(),category.getTitle());
                }
            });
        }
    }

}
