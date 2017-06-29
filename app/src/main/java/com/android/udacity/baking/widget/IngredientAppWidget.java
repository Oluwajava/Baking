package com.android.udacity.baking.widget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.android.udacity.baking.R;
import com.android.udacity.baking.activity.mainactivity.MainActivity;
import com.android.udacity.baking.activity.recipeactivity.RecipeActivity;
import com.android.udacity.baking.utils.Constants;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text) +"Bull Shit";
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_app_widget);

        SharedPreferences prefs = context.getSharedPreferences(Constants.Keys.shared_preference, Context.MODE_PRIVATE);
        String ingredient = prefs.getString(Constants.Keys.ingredients, null);

        views.setTextViewText(R.id.appwidget_text, ingredient);

        Intent intent = new Intent(context, RecipeActivity.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.ingredient_app_widget);
        Intent configIntent = new Intent(context, MainActivity.class);

        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);

        remoteViews.setOnClickPendingIntent(R.id.widget, configPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

