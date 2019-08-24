package com.example.graham.lutronswitch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.ConsumerIrManager;
import android.hardware.ConsumerIrManager.CarrierFrequencyRange;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    IRManager IR;
    LutronMaestroSwitch Switch = new LutronMaestroSwitch();

    boolean buttonPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            IR = new IRManager((ConsumerIrManager)this.getSystemService(this.CONSUMER_IR_SERVICE));

/*
            findViewById(R.id.button_Raise).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        sendCommand(LutronMaestroSwitch.Commands.Raise);

                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        buttonPressed = false;
                    }
                    return false;
                }
            });


            findViewById(R.id.button_Lower).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        sendCommand(LutronMaestroSwitch.Commands.Lower);

                    } else if (event.getAction() == MotionEvent.ACTION_UP) {
                        buttonPressed = false;
                    }
                    return false;
                }
            });
*/
        }
        catch (Exception ex){
            String message = ex.toString();
            showTextbox(message);
        }

    }

    public void btnFullOnClick(View view){
        try {
            IR.sendDataPronto(Switch.Frequency(), Switch.Function(LutronMaestroSwitch.Commands.FullOn));
        }
        catch (Exception ex){
            showTextbox(ex.toString());
        }
    }

    public void btnFullOffClick(View view){
        try {
            IR.sendDataPronto(Switch.Frequency(), Switch.Function(LutronMaestroSwitch.Commands.FullOff));
        }
        catch (Exception ex){
            showTextbox(ex.toString());
        }
    }

    public void btnFavoriteClick(View view){
        try {
            IR.sendDataPronto(Switch.Frequency(), Switch.Function(LutronMaestroSwitch.Commands.FavoriteScene));
        }
        catch (Exception ex){
            showTextbox(ex.toString());
        }
    }

    public void btnRaiseClick(View view){
        try {
            IR.sendDataPronto(Switch.Frequency(), Switch.Function(LutronMaestroSwitch.Commands.Raise));
        }
        catch (Exception ex){
            showTextbox(ex.toString());
        }
    }

    public void btnLowerClick(View view){
        try {
            IR.sendDataPronto(Switch.Frequency(), Switch.Function(LutronMaestroSwitch.Commands.Lower));
        }
        catch (Exception ex){
            showTextbox(ex.toString());
        }
    }



    public void sendCommand(LutronMaestroSwitch.Commands cmd){
        try {
            buttonPressed = true;

            Thread t1 = new Thread(new sendCommandAsync(cmd));
            t1.start();
            }
        catch (Exception ex){
            showTextbox(ex.toString());
        }
    }

    public class sendCommandAsync implements Runnable {
        private LutronMaestroSwitch.Commands parameter;
        public sendCommandAsync(LutronMaestroSwitch.Commands parameter) {
            this.parameter = parameter;
        }

        public void run() {
            try {
                while (buttonPressed){
                    IR.sendDataPronto(Switch.Frequency(), Switch.Function(this.parameter));
                }
            }
            catch (Exception ex){
                showTextbox(ex.toString());
            }
        }
    }



    public void showTextbox(String message){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Error");
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setNeutralButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }


}
