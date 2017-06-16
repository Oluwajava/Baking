package com.android.udacity.baking.fragments.recipestep;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.udacity.baking.R;
import com.android.udacity.baking.adapter.AbstractRecyclerView;
import com.android.udacity.baking.adapter.AbstractRecyclerViewHolder;
import com.android.udacity.baking.adapter.RecyclerViewItemClickListener;
import com.android.udacity.baking.model.RecipeSteps;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayokun on 6/14/2017.
 */

public class StepAdapter extends AbstractRecyclerView<RecipeSteps, StepAdapter.ViewHolder> {

    List<RecipeSteps> items;
    RecyclerViewItemClickListener<RecipeSteps> recyclerViewItemClickListener;

    public StepAdapter(int itemResourceId, List<RecipeSteps> items) {
        super(itemResourceId, items);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if(position%2 == 0) {
            holder.stepLayout.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.stepLayout.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public ViewHolder initializeViewHolder(View view) {
        return new ViewHolder(view, recyclerViewItemClickListener);
    }

    public void setListener(RecyclerViewItemClickListener<RecipeSteps> recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    public class ViewHolder extends AbstractRecyclerViewHolder<RecipeSteps> {
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.step_layout)
        LinearLayout stepLayout;

        RecipeSteps recipeSteps;

        public ViewHolder(View itemView, RecyclerViewItemClickListener recyclerViewItemClickListener) {
            super(itemView, recyclerViewItemClickListener);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bindView(RecipeSteps item) {
            recipeSteps = item;
            description.setText(item.getShortDescription());
        }

        @Override
        protected RecipeSteps getItem() {
            return recipeSteps;
        }
    }
}
