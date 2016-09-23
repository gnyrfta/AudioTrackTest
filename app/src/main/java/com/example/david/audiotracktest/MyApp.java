package com.example.david.audiotracktest;

import android.app.Application;
import android.content.Context;

/**
 * Created by david on 2016-05-17.
 */
public class MyApp extends Application {
    private static MyApp mInstance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
    }

    public static Context context()
    {
        return mInstance.getApplicationContext();
    }
}