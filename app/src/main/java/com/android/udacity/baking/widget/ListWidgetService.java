package com.android.udacity.baking.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Mayokun on 6/16/2017.
 */

public class ListWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }
}
