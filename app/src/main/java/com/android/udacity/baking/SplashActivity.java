package com.android.udacity.baking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.android.udacity.baking.base.BaseActivity;
import com.android.udacity.baking.activity.mainactivity.MainActivity;

/**
 * Created by Mayokun on 6/9/2017.
 */

public class SplashActivity extends BaseActivity{
    private static final int TIME_OUT = 1500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);
            finish();
        }, TIME_OUT);
    }

}
