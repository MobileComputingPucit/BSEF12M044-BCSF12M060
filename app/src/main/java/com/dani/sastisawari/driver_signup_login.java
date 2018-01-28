package com.dani.sastisawari;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

public class driver_signup_login extends AppCompatActivity {
    Button button1,button2;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.2F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_driver_signup_login);
        getSupportActionBar().hide();


        button1=(Button)findViewById(R.id.signup);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                Intent i = new Intent(driver_signup_login.this, driverSignup.class);
                startActivity(i);
            }
        });

        button2=(Button)findViewById(R.id.login);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                Intent i = new Intent(driver_signup_login.this, driverLogin.class);
                startActivity(i);
            }
        });
    }

}
