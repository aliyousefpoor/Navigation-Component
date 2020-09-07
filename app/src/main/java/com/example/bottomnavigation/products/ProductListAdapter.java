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
import com.example.bottomnavigation.data.model.Product;
import com.example.bottomnavigation.productdetail.ProductListener;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> products;
    private Context context;
    private ProductListener productListener;

    public ProductListAdapter(Context context, ProductListener productListener) {
        products = new ArrayList<>();
        this.context = context;
        this.productListener = productListener;
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
        productListViewHolder.onBind(products.get(position), context, productListener);
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

        public void onBind(final Product productsList, final Context context, final ProductListener productListener) {
            title.setText(productsList.getName());
            Glide.with(context).load(productsList.getAvatar()).into(avatar);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    productListener.onClick(productsList.getId());
                }
            });
        }
    }

    public void addList(List<Product> productLists) {
        final ProductListDiffCallback diffCallback = new ProductListDiffCallback(this.products, productLists);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        products = productLists;
        diffResult.dispatchUpdatesTo(this);

    }
}
