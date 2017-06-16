package com.android.udacity.baking.base;

import android.app.Application;

import com.android.udacity.baking.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Mayokun on 6/9/2017.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
