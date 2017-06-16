package com.android.udacity.baking.activity.recipeactivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.FrameLayout;

import com.android.udacity.baking.R;
import com.android.udacity.baking.adapter.NavigationListener;
import com.android.udacity.baking.adapter.RecyclerViewItemClickListener;
import com.android.udacity.baking.base.BaseActivity;
import com.android.udacity.baking.base.BaseModule;
import com.android.udacity.baking.fragments.recipedetail.RecipeDetailContract;
import com.android.udacity.baking.fragments.recipedetail.RecipeDetailFragment;
import com.android.udacity.baking.fragments.recipedetail.RecipeDetailPresenter;
import com.android.udacity.baking.fragments.recipestep.RecipeStepFragment;
import com.android.udacity.baking.fragments.recipestep.RecipeStepPresenter;
import com.android.udacity.baking.fragments.recipestep.di.DaggerRecipeStepComponent;
import com.android.udacity.baking.fragments.recipestep.di.RecipeStepModule;
import com.android.udacity.baking.model.Recipe;
import com.android.udacity.baking.model.RecipeSteps;
import com.android.udacity.baking.utils.ActivityUtils;
import com.android.udacity.baking.utils.Constants;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

/**
 * Created by Mayokun on 6/13/2017.
 */

public class RecipeActivity extends BaseActivity implements RecyclerViewItemClickListener<RecipeSteps>, NavigationListener{

    @Inject RecipeStepPresenter recipeStepPresenter;

    @BindView(R.id.side_menu)
    FrameLayout sideMenuLayout;

    @BindView(R.id.detail_menu) @Nullable
    FrameLayout detailMenu;

    Recipe recipeList;

    RecipeStepFragment recipeStepFragment;
    RecipeDetailFragment recipeDetailFragment;

    private int currentItem = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        ButterKnife.bind(this);

        if(getIntent().hasExtra(Constants.Keys.recipe)) {
            recipeList = getIntent().getParcelableExtra(Constants.Keys.recipe);
        }

        if(recipeStepFragment == null) {
            recipeStepFragment = RecipeStepFragment.newInstance(this, Arrays.asList(recipeList.getIngredients()), Arrays.asList(recipeList.getSteps()));
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), recipeStepFragment, R.id.side_menu);
        }

        DaggerRecipeStepComponent.builder()
                .baseModule(new BaseModule(this))
                .recipeStepModule(new RecipeStepModule(recipeStepFragment))
                .build()
                .inject(this);

        if(detailMenu != null) {
            recipeDetailFragment = RecipeDetailFragment.newInstance(this, Arrays.asList(recipeList.getSteps()).get(0));
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), recipeDetailFragment, R.id.detail_menu);
        }


    }

    @Override
    public void onItemClick(RecipeSteps item, int position) {

        currentItem = position;

        if(getResources().getBoolean(R.bool.isTablet)) {
            recipeDetailFragment.setDetail(item);
        } else {
            recipeDetailFragment = RecipeDetailFragment.newInstance(this, item);
            ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(), recipeDetailFragment, R.id.side_menu);
        }
    }


    @Override
    public void onNext() {
        currentItem++;
        if(currentItem >= recipeList.getSteps().length)
            currentItem = 0;
        recipeDetailFragment.setDetail(Arrays.asList(recipeList.getSteps()).get(currentItem));
    }

    @Override
    public void onPrevious() {
        currentItem--;
        if(currentItem < 0)
            currentItem = recipeList.getSteps().length - 1;
        recipeDetailFragment.setDetail(Arrays.asList(recipeList.getSteps()).get(currentItem));
    }
}
