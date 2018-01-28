package com.dani.sastisawari;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

public class forgotPassword extends AppCompatActivity {
EditText resetPass;
    Button passReset;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F,0.2F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().hide();

        passReset=(Button)findViewById(R.id.resetButton);
        passReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                resetmypass();
            }
        });

    }

    public void resetmypass()
    {
        resetPass = (EditText) findViewById(R.id.emailPassword);
        ParseUser.requestPasswordResetInBackground(resetPass.getText().toString(),
                new RequestPasswordResetCallback() {
                    public void done(ParseException e) {
                        if (e == null) {

                                AlertDialog alertDialog = new AlertDialog.Builder(forgotPassword.this).create();
                                alertDialog.setTitle("Email sent!");
                                alertDialog.setMessage("An email with password reset link was sent to your email");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }

                         else {



                                AlertDialog alertDialog = new AlertDialog.Builder(forgotPassword.this).create();
                                alertDialog.setTitle("Password Reset Failed!");
                                alertDialog.setMessage(e.getMessage());
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();

                        }
                    }
                });
    }
}
