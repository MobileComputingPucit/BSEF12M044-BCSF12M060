package com.dani.sastisawari;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

public class rider_signup_login extends AppCompatActivity {
    Button button4,button5;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.2F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_rider_signup_login);
        getSupportActionBar().hide();
        button4=(Button)findViewById(R.id.signup);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                Intent i = new Intent(rider_signup_login.this, riderSignup.class);
                startActivity(i);
            }
        });

        button5=(Button)findViewById(R.id.login1);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                Intent i = new Intent(rider_signup_login.this, riderLogin.class);
                startActivity(i);
            }
        });

    }
}
