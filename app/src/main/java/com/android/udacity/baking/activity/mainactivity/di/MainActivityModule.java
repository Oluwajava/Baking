package com.android.udacity.baking.activity.mainactivity.di;

import com.android.udacity.baking.activity.mainactivity.MainActivityContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mayokun on 6/9/2017.
 */
@Module
public class MainActivityModule {

    private MainActivityContract.View view;

    public MainActivityModule(MainActivityContract.View view) {
        this.view = view;
    }

    @Provides
    @Singleton
    MainActivityContract.View providesView() {
        return view;
    }
}
