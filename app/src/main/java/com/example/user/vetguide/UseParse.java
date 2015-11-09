package com.example.user.vetguide;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

/**
 * Created by USER on 09/10/2015.
 */
public class UseParse extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getResources().getString(R.string.parse_app_id), getResources().getString(R.string.parse_client_key));
        ParseFacebookUtils.initialize(this.getApplicationContext());



    }


}
