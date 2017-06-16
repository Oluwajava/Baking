package com.android.udacity.baking.fragments.recipe;

import com.android.udacity.baking.adapter.RecyclerViewItemClickListener;
import com.android.udacity.baking.base.BasePresenter;
import com.android.udacity.baking.base.BaseView;
import com.android.udacity.baking.data.local.AppDatabase;
import com.android.udacity.baking.model.Recipe;

import java.util.List;

/**
 * Created by Mayokun on 6/10/2017.
 */

public interface RecipeContract {

    interface View extends BaseView<Presenter> {
        void setAdapter(List<Recipe> recipeList, RecyclerViewItemClickListener<Recipe> recipeListener, AppDatabase localDb);
        void hideProgressBar();
        void showProgress();
    }

    interface Presenter extends BasePresenter {
        void getRecipes();
        void gotoRecipeStep(Recipe recipe);
    }
}
