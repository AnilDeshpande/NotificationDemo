package com.codetutor.notificationdemo;

import android.app.Application;

public class MyApplication extends Application {

    MyAppsNotificationManager  myAppsNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppsNotificationManager = MyAppsNotificationManager.getInstance(this);
        myAppsNotificationManager.registerNotificationChannelChannel();
    }

}
