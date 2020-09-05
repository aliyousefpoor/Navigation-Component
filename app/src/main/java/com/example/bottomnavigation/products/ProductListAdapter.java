package com.example.bottomnavigation.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.model.ProductsList;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ProductsList> products;
    private Context context;

    public ProductListAdapter(Context context) {
        products = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.product_list_adapter_layout, parent, false);
        return new ProductListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductListViewHolder productListViewHolder = (ProductListViewHolder) holder;
        productListViewHolder.onBind(products.get(position), context);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductListViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView title;
        CardView cardView;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.productAvatar);
            title = itemView.findViewById(R.id.productTitle);
            cardView = itemView.findViewById(R.id.productListCardView);
        }

        public void onBind(final ProductsList productsList, final Context context) {
            title.setText(productsList.getName());
            Glide.with(context).load(productsList.getAvatar()).into(avatar);
        }
    }

    public void addList(List<ProductsList> productLists) {
        final ProductListDiffCallback diffCallback = new ProductListDiffCallback(this.products, productLists);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        products = productLists;
        diffResult.dispatchUpdatesTo(this);

    }
}
