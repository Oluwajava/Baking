package com.android.udacity.baking.activity.mainactivity.di;

import com.android.udacity.baking.fragments.recipe.di.RecipeModule;
import com.android.udacity.baking.activity.mainactivity.MainActivity;
import com.android.udacity.baking.base.BaseModule;
import com.android.udacity.baking.activity.mainactivity.MainActivityContract;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mayokun on 6/9/2017.
 */
@Singleton
@Component(modules = {BaseModule.class, RecipeModule.class})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

}
