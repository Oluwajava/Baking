package com.android.udacity.baking.fragments.recipedetail;

import javax.inject.Inject;

/**
 * Created by Mayokun on 6/14/2017.
 */

public class RecipeDetailPresenter implements RecipeDetailContract.Presenter {

    private RecipeDetailContract.View view;

    @Inject
    public RecipeDetailPresenter(RecipeDetailContract.View view) {
        this.view = view;
    }

    @Inject
    void setupListeners() {
        view.setPresenter(this);
    }
    @Override
    public void start() {

    }
}
