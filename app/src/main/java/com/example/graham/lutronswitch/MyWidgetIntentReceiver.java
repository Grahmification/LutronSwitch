package com.example.graham.lutronswitch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.ConsumerIrManager;
import android.widget.RemoteViews;

public class MyWidgetIntentReceiver extends BroadcastReceiver {

    LutronMaestroSwitch Switch = new LutronMaestroSwitch();

    //from tutorial: https://looksok.wordpress.com/2012/12/15/android-complete-widget-tutorial-including-source-code/

    @Override
    public void onReceive(Context context, Intent intent) {
        //if(intent.getAction().equals("LutronWidget.intent.action.FullOn")){
            //updateWidgetPictureAndButtonListener(context);
        //}

        sendDataAndUpdateListener(context, intent);
    }

    private void updateWidgetPictureAndButtonListener(Context context) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.lutron_widget);
        //remoteViews.setImageViewResource(R.id.widget_image, getImageToSet());

        IRManager IR = new IRManager((ConsumerIrManager)context.getSystemService(MainActivity.CONSUMER_IR_SERVICE));
        IR.sendDataPronto(Switch.Frequency(), Switch.Function(LutronMaestroSwitch.Commands.FullOn));

        //REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
        remoteViews.setOnClickPendingIntent(R.id.button_FullOn_Widget, LutronWidget.buildButtonPendingIntent(context));
        LutronWidget.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
    }


    private void sendDataAndUpdateListener(Context context, Intent intent){
        int ViewID = intent.getIntExtra("ViewID", 0);
        IRManager IR = new IRManager((ConsumerIrManager)context.getSystemService(MainActivity.CONSUMER_IR_SERVICE));

        switch (ViewID){
            case R.id.button_FullOn_Widget:{
                IR.sendDataPronto(Switch.Frequency(), Switch.Function(LutronMaestroSwitch.Commands.FullOn));
                break;
            }
            case R.id.button_FullOff_Widget:{
                IR.sendDataPronto(Switch.Frequency(), Switch.Function(LutronMaestroSwitch.Commands.FullOff));
                break;
            }
            case R.id.button_Raise_Widget:{
                IR.sendDataPronto(Switch.Frequency(), Switch.Function(LutronMaestroSwitch.Commands.Raise));
                break;
            }
            case R.id.button_Lower_Widget:{
                IR.sendDataPronto(Switch.Frequency(), Switch.Function(LutronMaestroSwitch.Commands.Lower));
                break;
            }
            case R.id.button_Favorite_Widget:{
                IR.sendDataPronto(Switch.Frequency(), Switch.Function(LutronMaestroSwitch.Commands.FavoriteScene));
                break;
            }
            default:
                break;
        }

        //REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
        updateButtonListner(context, R.id.button_FullOn_Widget);
        updateButtonListner(context, R.id.button_FullOff_Widget);
        updateButtonListner(context, R.id.button_Raise_Widget);
        updateButtonListner(context, R.id.button_Lower_Widget);
        updateButtonListner(context, R.id.button_Favorite_Widget);
    }

    private void updateButtonListner(Context context, int ViewID){
        //REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.lutron_widget);
        remoteViews.setOnClickPendingIntent(ViewID, LutronWidget.buildButtonPendingIntent2(context, ViewID));
        LutronWidget.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
    }

}