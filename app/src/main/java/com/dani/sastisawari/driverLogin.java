package com.dani.sastisawari;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class driverLogin extends AppCompatActivity {
Button driver_login_button;
    ProgressDialog progress;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.2F);
    TextView forgotText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_driver_login);
        progress = new ProgressDialog(driverLogin.this);
        getSupportActionBar().hide();

        driver_login_button=(Button)findViewById(R.id.driver_login_botton);
        driver_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(buttonClick);
                progress.setMessage("Authenticating user....");
                progress.setCancelable(false);
                progress.show();
                loginDriver();
            }
        });

        forgotText = (TextView) findViewById(R.id.dforgotpass);
        forgotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                Intent i = new Intent(driverLogin.this, forgotPassword.class);
                startActivity(i);
            }
        });
    }



    public void loginDriver()
    {
        EditText driverUsername, driverPassword;
        driverUsername = (EditText) findViewById(R.id.dusername);
        driverPassword = (EditText) findViewById(R.id.dpass);


        if(driverUsername.getText().toString().matches("") || driverPassword.getText().toString().matches(""))
        {
            progress.dismiss();
            Toast.makeText(this,"Username and Password must be entered to Login",Toast.LENGTH_LONG).show();
        }
        else
        {

            ParseUser.logInInBackground(driverUsername.getText().toString(), driverPassword.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if(parseUser != null)
                    {
                        if(parseUser.getBoolean("emailVerified")) {
                            progress.dismiss();
                            Toast.makeText(getApplicationContext(),"Login Successful!",Toast.LENGTH_LONG).show();
                            Intent launchNextActivity;
                            launchNextActivity = new Intent(driverLogin.this, ViewRequestsActivity.class);
                            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(launchNextActivity);
                        }
                        else
                        {
                            progress.dismiss();
                            parseUser.logOut();
                            AlertDialog alertDialog = new AlertDialog.Builder(driverLogin.this).create();
                            alertDialog.setTitle("Login Failed!");
                            alertDialog.setMessage("Please Verify Your Email first");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                    }
                    else
                    {
                        progress.dismiss();
                        Toast.makeText(driverLogin.this,"Login Failed: "+e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }
}
