package com.example.bottomnavigation.productdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.data.model.Comment;

import java.util.List;

public class ProductCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Comment> comments;

    public ProductCommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.product_comment_adapter, parent, false);
        return new ProductCommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ProductCommentViewHolder detailViewHolder = (ProductCommentViewHolder) holder;
        detailViewHolder.onBind(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ProductCommentViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView comment;

        public ProductCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cmCardView);
            comment = itemView.findViewById(R.id.comment);
        }

        public void onBind(Comment comments) {
            comment.setText(comments.getComment_text());
        }
    }
}
