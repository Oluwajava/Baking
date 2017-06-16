package com.android.udacity.baking.fragments.recipe.di;

import com.android.udacity.baking.fragments.recipe.RecipeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mayokun on 6/10/2017.
 */
@Module
public class RecipeModule {

    private final RecipeContract.View view;

    public RecipeModule(RecipeContract.View view) {
        this.view = view;
    }

    @Provides
    RecipeContract.View providesRecipe() {
        return view;
    }

}
