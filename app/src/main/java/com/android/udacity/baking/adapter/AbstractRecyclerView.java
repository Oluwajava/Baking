package com.android.udacity.baking.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Mayokun on 6/12/2017.
 */

public abstract class AbstractRecyclerView<T, V extends AbstractRecyclerViewHolder> extends RecyclerView.Adapter<V>{

    private List<T> items;
    private int itemResourceId;

    public AbstractRecyclerView(int itemResourceId, List<T> items) {
        this.items = items;
        this.itemResourceId = itemResourceId;
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(itemResourceId, parent, false);

        V viewHolder = this.initializeViewHolder(view);
        return viewHolder;
    }

    public abstract V initializeViewHolder(View view);

    @Override
    public void onBindViewHolder(V holder, int position) {
        holder.bindView(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
