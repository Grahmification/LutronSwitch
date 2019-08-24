package com.example.graham.lutronswitch;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.ConsumerIrManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link LutronWidgetConfigureActivity LutronWidgetConfigureActivity}
 */
public class LutronWidget extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = LutronWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.lutron_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

            setupButtonRemoteView(context,R.id.button_FullOn_Widget);
            setupButtonRemoteView(context,R.id.button_FullOff_Widget);
            setupButtonRemoteView(context,R.id.button_Raise_Widget);
            setupButtonRemoteView(context,R.id.button_Lower_Widget);
            setupButtonRemoteView(context,R.id.button_Favorite_Widget);

        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            LutronWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }
    @Override
    public void onEnabled(Context context) {
        //RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.lutron_widget);
        //pushWidgetUpdate(context, remoteViews);
    }
    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }




    public static void setupButtonRemoteView(Context context, int ViewID) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.lutron_widget);
        remoteViews.setOnClickPendingIntent(ViewID, buildButtonPendingIntent2(context, ViewID));
        pushWidgetUpdate(context, remoteViews);

    }

    public static PendingIntent buildButtonPendingIntent2(Context context, int ViewID) {
        Intent intent = new Intent();

        switch (ViewID){
            case R.id.button_FullOn_Widget:{
                intent.setAction("LutronWidget.intent.action.FullOn");
                break;
            }
            case R.id.button_FullOff_Widget:{
                intent.setAction("LutronWidget.intent.action.FullOff");
                break;
            }
            case R.id.button_Raise_Widget:{
                intent.setAction("LutronWidget.intent.action.Raise");
                break;
            }
            case R.id.button_Lower_Widget:{
                intent.setAction("LutronWidget.intent.action.Lower");
                break;
            }
            case R.id.button_Favorite_Widget:{
                intent.setAction("LutronWidget.intent.action.Favorite");
                break;
            }
            default:
                break;
        }

        intent.putExtra("ViewID", ViewID);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }




    //from tutorial: https://looksok.wordpress.com/2012/12/15/android-complete-widget-tutorial-including-source-code/

    public static PendingIntent buildButtonPendingIntent(Context context) {
        Intent intent = new Intent();
        intent.setAction("LutronWidget.intent.action.FullOn");
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
        ComponentName myWidget = new ComponentName(context, LutronWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(myWidget, remoteViews);
    }

}



