package com.android.udacity.baking.fragments.recipe;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.udacity.baking.R;
import com.android.udacity.baking.adapter.AbstractRecyclerView;
import com.android.udacity.baking.adapter.AbstractRecyclerViewHolder;
import com.android.udacity.baking.adapter.RecyclerViewItemClickListener;
import com.android.udacity.baking.data.local.AppDatabase;
import com.android.udacity.baking.data.local.GetFavourite;
import com.android.udacity.baking.model.Favourite;
import com.android.udacity.baking.model.Recipe;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mayokun on 6/12/2017.
 */

public class RecipeAdapter extends AbstractRecyclerView<Recipe, RecipeAdapter.ViewHolder> {
    Recipe recipe;
    RecyclerViewItemClickListener<Recipe> recipeClickListener;
    List<Recipe> items;
    AppDatabase localDb;

    public RecipeAdapter(int itemResourceId, List<Recipe> items) {
        super(itemResourceId, items);
        this.items = items;
    }


    @Override
    public ViewHolder initializeViewHolder(View view) {
        return new ViewHolder(view, recipeClickListener);
    }

    public void setListener(RecyclerViewItemClickListener<Recipe> recipeRecyclerViewItemClickListener) {
        recipeClickListener = recipeRecyclerViewItemClickListener;
    }

    public void setLocalDb(AppDatabase localDb) {
        this.localDb = localDb;
    }

    public class ViewHolder extends AbstractRecyclerViewHolder<Recipe> {

        private Context context;
        private Recipe item;
        @BindView(R.id.ingredient_text_view) TextView ingredientText;
        @BindView(R.id.steps_text_view) TextView stepsText;
        @BindView(R.id.recipe_name) TextView recipeText;
        @BindView(R.id.favourite_image_view) ImageView favouriteImageView;
        @BindView(R.id.recipe_image) ImageView recipeImage;

        public ViewHolder(View itemView, RecyclerViewItemClickListener recyclerViewItemClickListener) {
            super(itemView, recyclerViewItemClickListener);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();

            favouriteImageView.setOnClickListener((view) -> {
                Favourite fav = new Favourite(items.get(getAdapterPosition()).getId(), items.get(getAdapterPosition()).getName());
                if(GetFavourite.isFavourite(fav, localDb)) {
                    GetFavourite.deleteFavourite(fav, localDb);
                } else {
                    GetFavourite.addFavourite(fav, localDb);
                }

                GetFavourite.setFavouriteStatus(fav, localDb, favouriteImageView);
            });

            itemView.setOnClickListener((view) -> {
                recyclerViewItemClickListener.onItemClick(items.get(getAdapterPosition()), getAdapterPosition());
            });

        }

        @Override
        protected void bindView(Recipe item) {
            this.item = item;
            recipeText.setText(item.getName());
            ingredientText.setText(String.valueOf(item.getIngredients().length));
            stepsText.setText(String.valueOf(item.getSteps().length));
            Glide.with(context)
                    .load(item.getImage())
                    .centerCrop()
                    .into(recipeImage);

            GetFavourite.setFavouriteStatus(new Favourite(item.getId(), item.getName()), localDb, favouriteImageView);

        }

        @Override
        protected Recipe getItem() {
            return recipe;
        }


    }
}
