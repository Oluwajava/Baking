package com.android.udacity.baking.fragments.recipestep.di;

import com.android.udacity.baking.activity.recipeactivity.RecipeActivity;
import com.android.udacity.baking.base.BaseModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mayokun on 6/15/2017.
 */
@Singleton
@Component(modules = {BaseModule.class, RecipeStepModule.class})
public interface RecipeStepComponent {

    void inject(RecipeActivity recipeActivity);

}
