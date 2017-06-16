package com.android.udacity.baking.fragments.recipe;

import android.content.Context;
import android.content.Intent;

import com.android.udacity.baking.adapter.RecyclerViewItemClickListener;
import com.android.udacity.baking.data.local.AppDatabase;
import com.android.udacity.baking.data.remote.GetRecipe;
import com.android.udacity.baking.data.remote.RecipeService;
import com.android.udacity.baking.model.Recipe;
import com.android.udacity.baking.activity.recipeactivity.RecipeActivity;
import com.android.udacity.baking.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Mayokun on 6/10/2017.
 */

public class RecipePresenter implements RecipeContract.Presenter, RecyclerViewItemClickListener<Recipe> {

    private static final String TAG = RecipePresenter.class.getSimpleName();
    RecipeContract.View view;

    @Inject
    Retrofit retrofit;
    @Inject
    AppDatabase localDb;
    @Inject
    Context context;

    private RecyclerViewItemClickListener<Recipe> recipeListener = this;

    @Inject
    public RecipePresenter(RecipeContract.View view) {
        this.view = view;
    }

    @Inject
    void setupListeners() {
        view.setPresenter(this);
    }

    @Override
    public void start() {
        getRecipes();
    }

    @Override
    public void getRecipes() {
        RecipeService recipeService = retrofit.create(RecipeService.class);
        Call<List<Recipe>> recipe = GetRecipe.getRecipeList(recipeService);

        recipe.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                view.setAdapter(response.body(), recipeListener, localDb);
                view.hideProgressBar();//hide loading
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                view.hideProgressBar();//hide loading
            }
        });

    }

    @Override
    public void gotoRecipeStep(Recipe recipe) {
        Intent recipeActivityIntent = new Intent(context, RecipeActivity.class);
        recipeActivityIntent.putExtra(Constants.Keys.recipe, recipe);
        context.startActivity(recipeActivityIntent);
    }

    @Override
    public void onItemClick(Recipe item, int position) {
        gotoRecipeStep(item);
    }
}
