package com.android.udacity.baking.fragments.recipedetail;

import com.android.udacity.baking.base.BasePresenter;
import com.android.udacity.baking.base.BaseView;
import com.android.udacity.baking.model.RecipeSteps;

/**
 * Created by Mayokun on 6/14/2017.
 */

public interface RecipeDetailContract {

    interface View extends BaseView<Presenter> {
        void setDetail(RecipeSteps recipeSteps);
    }

    interface Presenter extends BasePresenter {

    }
}
