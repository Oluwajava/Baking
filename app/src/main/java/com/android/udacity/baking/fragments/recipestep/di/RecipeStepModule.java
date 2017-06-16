package com.android.udacity.baking.fragments.recipestep.di;

import com.android.udacity.baking.fragments.recipestep.RecipeStepContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mayokun on 6/15/2017.
 */
@Module
public class RecipeStepModule {

    private final RecipeStepContract.View view;

    public RecipeStepModule(RecipeStepContract.View view) {
        this.view = view;
    }

    @Provides
    @Singleton
    RecipeStepContract.View providesView() {
        return view;
    }
}
