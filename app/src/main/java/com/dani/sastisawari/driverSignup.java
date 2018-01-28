package com.dani.sastisawari;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class driverSignup extends AppCompatActivity {
Button driver_signup_button;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.2F);
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_driver_signup);
        progress = new ProgressDialog(driverSignup.this);
        getSupportActionBar().hide();

        driver_signup_button=(Button)findViewById(R.id.driver_signup_button);
        driver_signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                progress.setMessage("Registering user....");
                progress.setCancelable(false);
                progress.show();
                signUpDriver();
            }
        });


    }
    public void signUpDriver()
    {
        EditText userName, email, password, mobNo;
        userName = (EditText) findViewById(R.id.duname);
        email     = (EditText) findViewById(R.id.demail);
        password  = (EditText) findViewById(R.id.dpass);
        mobNo     = (EditText) findViewById(R.id.dmobno);

        if(userName.getText().toString().matches("") || email.getText().toString().matches("") || password.getText().toString().matches("") || mobNo.getText().toString().matches(""))
        {
            progress.dismiss();
            Toast.makeText(this,"All fields must be filled for SignUp",Toast.LENGTH_LONG).show();
        }
        else
        {
            ParseUser user = new ParseUser();
            user.setUsername(userName.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.put("mobNo", mobNo.getText().toString());
            user.put("riderOrDriver","driver");
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null)
                    {
                        progress.dismiss();
                        AlertDialog alertDialog = new AlertDialog.Builder(driverSignup.this).create();
                        alertDialog.setTitle("SignUp Successful!");
                        alertDialog.setMessage("Please verify your email before Login");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();

                    }
                    else
                    {
                        progress.dismiss();
                        Toast.makeText(driverSignup.this,"SignUp Failed: "+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}
