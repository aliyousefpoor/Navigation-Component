package com.example.bottomnavigation.products;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.bottomnavigation.data.model.ProductsList;

import java.util.List;

public class ProductListDiffCallback extends DiffUtil.Callback {
    private List<ProductsList> oldProductsList;
    private List<ProductsList> newProductsList;

    public ProductListDiffCallback(List<ProductsList> oldProductsList, List<ProductsList> newProductsList) {
        this.oldProductsList = oldProductsList;
        this.newProductsList = newProductsList;
    }

    @Override
    public int getOldListSize() {
        return oldProductsList.size();
    }

    @Override
    public int getNewListSize() {
        return newProductsList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldProductsList.get(oldItemPosition).getId() == newProductsList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ProductsList oldList =oldProductsList.get(oldItemPosition);
        ProductsList newList = newProductsList.get(newItemPosition);
        return oldList.getName().equals(newList.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
