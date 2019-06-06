package com.codetutor.notificationdemo;

import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFireBaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(getString(R.string.DEBUG_TAG),"New token: "+s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(getString(R.string.DEBUG_TAG),"Remote Message received");

        ((MyApplication)getApplication()).triggerNotificationWithBackStack(NotificationDetailsActivity.class,
                getString(R.string.NEWS_CHANNEL_ID),
                remoteMessage.getNotification().getTitle(),
                remoteMessage.getNotification().getBody(),
                "This notification is from FCM console ",
                NotificationCompat.PRIORITY_HIGH,
                true,
                getResources().getInteger(R.integer.notificationId),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
