package com.android.udacity.baking.widget;

import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.android.udacity.baking.R;
import com.android.udacity.baking.base.BaseModule;
import com.android.udacity.baking.data.remote.GetRecipe;
import com.android.udacity.baking.data.remote.RecipeService;
import com.android.udacity.baking.model.Recipe;
import com.android.udacity.baking.widget.di.DaggerWidgetComponent;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Mayokun on 6/16/2017.
 */

public class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    @Inject
    Retrofit retrofit;

    Context context;

    Recipe recipe;

    public ListRemoteViewsFactory(Context context) {
        this.context = context;

        DaggerWidgetComponent.builder()
                .baseModule(new BaseModule(context))
                .build()
                .inject(this);


    }

    public void getRecipes() {
        RecipeService recipeService = retrofit.create(RecipeService.class);
        Call<List<Recipe>> recipe = GetRecipe.getRecipeList(recipeService);

        final Recipe[] result = new Recipe[1];

        recipe.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                result[0] = response.body().get(0);
                Log.d("Widget", response.body().get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d("Widget", "Failed");
            }
        });

        this.recipe = result[0];

    }

    @Override
    public void onCreate() {


    }

    @Override
    public void onDataSetChanged() {
        getRecipes();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if (recipe == null) return 0;
        return recipe.getIngredients().length;
    }

    @Override
    public RemoteViews getViewAt(int i) {

        if (recipe == null) return null;

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_view);

        views.setTextViewText(R.id.ingredient, String.valueOf(recipe.getIngredients()[0].getIngredient()));
        views.setTextViewText(R.id.quantity, String.valueOf(recipe.getIngredients()[0].getQuantity()));
        views.setTextViewText(R.id.measure, String.valueOf(recipe.getIngredients()[0].getMeasure()));

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
