package com.dani.sastisawari;

/**
 * Created by Dani on 5/8/2017.
 */
import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;


public class starterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                        .applicationId("qheyEnDAd2MplQX6YsN4IdofpFQyE5EbHv2NCw80")
                        .clientKey("gNex4FXlFXBd2NoYY9yOvktWG23bN5G6nosEVAtw")
                        .server("https://parseapi.back4app.com/")
                        .build()
        );




        //ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}

