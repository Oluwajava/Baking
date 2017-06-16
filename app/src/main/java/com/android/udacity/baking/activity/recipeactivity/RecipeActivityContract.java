package com.android.udacity.baking.activity.recipeactivity;

import com.android.udacity.baking.base.BasePresenter;
import com.android.udacity.baking.base.BaseView;

/**
 * Created by Mayokun on 6/14/2017.
 */

public interface RecipeActivityContract {

    interface  View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
