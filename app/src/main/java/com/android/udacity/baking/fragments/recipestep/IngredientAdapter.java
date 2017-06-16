package com.android.udacity.baking.fragments.recipestep;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.udacity.baking.R;
import com.android.udacity.baking.adapter.AbstractRecyclerView;
import com.android.udacity.baking.adapter.AbstractRecyclerViewHolder;
import com.android.udacity.baking.adapter.RecyclerViewItemClickListener;
import com.android.udacity.baking.model.RecipeIngredients;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayokun on 6/14/2017.
 */

public class IngredientAdapter extends AbstractRecyclerView<RecipeIngredients, IngredientAdapter.ViewHolder> {

    private List<RecipeIngredients> recipeIngredientsList;

    public IngredientAdapter(int itemResourceId, List<RecipeIngredients> items) {
        super(itemResourceId, items);
        this.recipeIngredientsList = items;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindView(recipeIngredientsList.get(position));

        if(position%2 == 0) {
            holder.ingredientLayout.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.ingredientLayout.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public ViewHolder initializeViewHolder(View view) {
        return new ViewHolder(view);
    }

    public class ViewHolder extends AbstractRecyclerViewHolder<RecipeIngredients> {

        @BindView(R.id.measure)
        TextView measure;
        @BindView(R.id.quantity)
        TextView quantity;
        @BindView(R.id.ingredient)
        TextView ingredient;
        @BindView(R.id.ingredient_layout)
        LinearLayout ingredientLayout;
        RecipeIngredients recipeIngredients;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void bindView(RecipeIngredients item) {
            measure.setText(item.getMeasure());
            quantity.setText(String.valueOf(item.getQuantity()));
            ingredient.setText(item.getIngredient());
        }

        @Override
        protected RecipeIngredients getItem() {
            return recipeIngredients;
        }
    }
}
