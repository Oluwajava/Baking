package com.android.udacity.baking.activity.mainactivity;

import com.android.udacity.baking.base.BasePresenter;
import com.android.udacity.baking.base.BaseView;

/**
 * Created by Mayokun on 6/9/2017.
 */

public interface MainActivityContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }
}
