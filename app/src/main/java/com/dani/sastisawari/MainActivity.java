package com.dani.sastisawari;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

Button button3;
private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.2F);
    public void redirectActivity()
    {
        if(ParseUser.getCurrentUser().get("riderOrDriver").equals("rider"))
        {
            Intent launchNextActivity;
            launchNextActivity = new Intent(getApplicationContext(), RiderActivity.class);
            startActivity(launchNextActivity);
        }
        else if(ParseUser.getCurrentUser().get("riderOrDriver").equals("driver"))
        {
            Intent i = new Intent(getApplicationContext(),ViewRequestsActivity.class);
            startActivity(i);
        }

    }

    @Override
    public void onBackPressed() {
            finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        checkGPSStatus();

        if (ParseUser.getCurrentUser() == null) {

            Switch onOffSwitch = (Switch) findViewById(R.id.switch1);
            onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                           if (isChecked) {
                                                               button3 = (Button) findViewById(R.id.getStarted);
                                                               button3.setOnClickListener(new View.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(View v) {
                                                                       v.startAnimation(buttonClick);
                                                                       Intent i = new Intent(MainActivity.this, rider_signup_login.class);
                                                                       startActivity(i);
                                                                   }
                                                               });
                                                           } else if(!(isChecked)) {
                                                               button3 = (Button) findViewById(R.id.getStarted);
                                                               button3.setOnClickListener(new View.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(View v) {
                                                                       v.startAnimation(buttonClick);
                                                                       Intent i = new Intent(MainActivity.this, driver_signup_login.class);
                                                                       startActivity(i);
                                                                   }
                                                               });
                                                           }
                                                       }

                                                   }

            );


        }
        else
        {
            redirectActivity();
        }
    }


    private void checkGPSStatus() {
        LocationManager locationManager = null;
        boolean gps_enabled = false;
        boolean network_enabled = false;
        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        if (!gps_enabled && !network_enabled) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setMessage("GPS not enabled");
            dialog.setPositiveButton("eable", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //this will navigate user to the device location settings screen
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            AlertDialog alert = dialog.create();
            alert.show();
        }
    }

    }
