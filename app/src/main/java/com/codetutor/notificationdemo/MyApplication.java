package com.codetutor.notificationdemo;

import android.app.Application;

public class MyApplication extends Application {

    MyAppsNotificationManager  myAppsNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppsNotificationManager = MyAppsNotificationManager.getInstance(this);
        myAppsNotificationManager.registerNotificationChannelChannel(
                getString(R.string.NEWS_CHANNEL_ID),
                getString(R.string.CHANNEL_NEWS),
                getString(R.string.CHANNEL_DESCRIPTION));
    }

}
