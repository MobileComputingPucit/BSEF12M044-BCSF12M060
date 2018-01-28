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

public class riderSignup extends AppCompatActivity {
Button rider_signup_button;
    ProgressDialog progress;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.2F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_rider_signup);
        getSupportActionBar().hide();

        progress = new ProgressDialog(riderSignup.this);

        rider_signup_button=(Button)findViewById(R.id.rider_signup_button);
        rider_signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                progress.setMessage("Authenticating user....");
                progress.setCancelable(false);
                progress.show();
                signUpRider();
            }
        });

    }

    public void signUpRider()
    {
        EditText userName, email, password, mobNo;
        userName = (EditText) findViewById(R.id.runame);
        email     = (EditText) findViewById(R.id.remail);
        password  = (EditText) findViewById(R.id.rpass);
        mobNo     = (EditText) findViewById(R.id.rmobno);

        if(userName.getText().toString().matches("") || email.getText().toString().matches("") || password.getText().toString().matches("") || mobNo.getText().toString().matches(""))
        {
            progress.dismiss();
            Toast.makeText(this, "All fields must be filled for SignUp", Toast.LENGTH_LONG).show();
        }
        else
        {
            ParseUser user = new ParseUser();
            user.setUsername(userName.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(password.getText().toString());
            user.put("mobNo", mobNo.getText().toString());
            user.put("riderOrDriver","rider");
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null)
                    {
                        progress.dismiss();
                        AlertDialog alertDialog = new AlertDialog.Builder(riderSignup.this).create();
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
                        Toast.makeText(riderSignup.this,"SignUp Failed: "+e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
