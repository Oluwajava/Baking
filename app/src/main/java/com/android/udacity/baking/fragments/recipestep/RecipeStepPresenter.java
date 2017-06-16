package com.android.udacity.baking.fragments.recipestep;

import javax.inject.Inject;

/**
 * Created by Mayokun on 6/14/2017.
 */

public class RecipeStepPresenter implements RecipeStepContract.Presenter {

    RecipeStepContract.View view;

    @Inject
    public RecipeStepPresenter(RecipeStepContract.View view) {
        this.view = view;
    }

    @Inject
    void setupListener() {
        view.setPresenter(this);
    }
    @Override
    public void start() {

    }
}
