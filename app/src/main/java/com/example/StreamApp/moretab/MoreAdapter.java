package com.example.StreamApp.moretab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StreamApp.R;
import com.example.StreamApp.data.model.MoreModel;


import java.util.List;

public class MoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    List<MoreModel> moreLists;
    private MoreItemListener moreItemListener;


    public MoreAdapter(List<MoreModel> moreLists, Context context, MoreItemListener moreItemListener) {
        this.context = context;
        this.moreLists = moreLists;
        this.moreItemListener = moreItemListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.more_adapter, parent, false);
        return new MoreViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final MoreViewHolder moreViewHolder = (MoreViewHolder) holder;
        MoreModel item = moreLists.get(position);
        moreViewHolder.onBind(item, context, position, moreItemListener);
    }

    @Override
    public int getItemCount() {
        return moreLists.size();
    }

    public static class MoreViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;


        public MoreViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            cardView = itemView.findViewById(R.id.card_view);

        }

        public void onBind(final MoreModel moreList, final Context context, final int position, final MoreItemListener moreItemListener) {
            textView.setText(moreList.title);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, moreList.title + " کلیک شد ", Toast.LENGTH_SHORT).show();
                    moreItemListener.onClick(moreList);
                }
            });
        }

    }
}
