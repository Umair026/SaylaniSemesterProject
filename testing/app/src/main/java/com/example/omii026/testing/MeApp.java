package com.example.omii026.testing;

import android.app.Application;
import android.content.Context;

import com.example.omii026.testing.Class.User;
import com.firebase.client.Firebase;

/**
 * Created by Omii026 on 9/11/2015.
 */
public class MeApp extends Application {

    final static private String APP_KEY = "0rsqerm8ejdsucf";
    final static private String APP_SECRET = "drd7bz4ta4wtvuw";

    private static User appUser;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        Firebase.setAndroidContext(this);    // context defining

    }

    public static void setUser(User user){
        appUser = user;
    }

    public static User getAppUser(){
        return appUser;
    }
}
