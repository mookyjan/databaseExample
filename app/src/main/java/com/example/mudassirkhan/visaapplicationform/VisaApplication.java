package com.example.mudassirkhan.visaapplicationform;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.example.mudassirkhan.visaapplicationform.sqlite.DatabaseManager;


/**
 * Created by waqas on 1/9/17.
 */

public class VisaApplication extends Application {
    private static Context mContext;


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        DatabaseManager.getInstance(); //create database
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getAppContext() {
        return mContext;
    }
}
