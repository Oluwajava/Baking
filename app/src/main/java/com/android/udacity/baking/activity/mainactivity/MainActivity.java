package com.android.udacity.baking.activity.mainactivity;

import android.content.Context;
import android.os.Bundle;

import com.android.udacity.baking.R;
import com.android.udacity.baking.activity.mainactivity.di.DaggerMainActivityComponent;
import com.android.udacity.baking.base.BaseActivity;
import com.android.udacity.baking.base.BaseModule;
import com.android.udacity.baking.fragments.recipe.RecipeFragment;
import com.android.udacity.baking.fragments.recipe.RecipePresenter;
import com.android.udacity.baking.fragments.recipe.di.RecipeModule;
import com.android.udacity.baking.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @Inject
    Context context;

    @Inject
    RecipePresenter recipePresenter;

    RecipeFragment recipeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        recipeFragment = (RecipeFragment) getSupportFragmentManager().findFragmentById(R.id.recipe_layout);

        if (recipeFragment == null) {
            recipeFragment = RecipeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), recipeFragment, R.id.recipe_layout);
        }

        DaggerMainActivityComponent.builder()
                .baseModule(new BaseModule(this))
                .recipeModule(new RecipeModule(recipeFragment))
                .build()
                .inject(this);

    }
}
