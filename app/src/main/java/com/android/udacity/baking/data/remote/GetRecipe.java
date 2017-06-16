package com.android.udacity.baking.data.remote;

import com.android.udacity.baking.adapter.AbstractRemoteCall;
import com.android.udacity.baking.model.Recipe;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Mayokun on 6/12/2017.
 */

public class GetRecipe extends AbstractRemoteCall<Recipe, RecipeService> {
    @Override
    protected Call<List<Recipe>> makeListCall(RecipeService recipeService) {
        Call<List<Recipe>> call = recipeService.getRecipies();
        return call;
    }

    @Override
    protected Call<Recipe> makeCall(RecipeService recipeService) {
        return null;
    }


    public  static Call<List<Recipe>> getRecipeList(RecipeService recipeService) {
        return new GetRecipe().makeListCall(recipeService);
    }
}
