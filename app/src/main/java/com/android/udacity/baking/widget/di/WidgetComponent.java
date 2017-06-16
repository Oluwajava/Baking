package com.android.udacity.baking.widget.di;

import com.android.udacity.baking.base.BaseModule;
import com.android.udacity.baking.widget.ListRemoteViewsFactory;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mayokun on 6/16/2017.
 */
@Singleton
@Component(modules = {BaseModule.class})
public interface WidgetComponent {

    void inject(ListRemoteViewsFactory listRemoteViewsFactory);
}
