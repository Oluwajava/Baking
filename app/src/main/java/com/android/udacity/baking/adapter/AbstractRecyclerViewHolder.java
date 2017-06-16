package com.android.udacity.baking.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Mayokun on 6/12/2017.
 */

public abstract class AbstractRecyclerViewHolder<T> extends RecyclerView.ViewHolder {
    public AbstractRecyclerViewHolder(View itemView, RecyclerViewItemClickListener<T> recyclerViewItemClickListener) {
        super(itemView);

        itemView.setOnClickListener((view) -> {
            recyclerViewItemClickListener.onItemClick(getItem(), getAdapterPosition());
        });

    }

    public AbstractRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void bindView(T item);

    protected abstract T getItem();
}
