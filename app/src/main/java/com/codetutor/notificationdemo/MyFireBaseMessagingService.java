package com.codetutor.notificationdemo;

import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Set;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFireBaseMessagingService.class.getSimpleName();

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(TAG," The New token has been generated: "+s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(TAG,"onMessageReceived invoked");

        ((MyApplication)getApplication()).triggerNotificationWithBackStack(NotificationDetailsActivity.class,
                getString(R.string.NEWS_CHANNEL_ID),
                "Sample Notification",
                "This is a sample notification app",
                "This is a sample notification created by Codetutor for demonstration of how to trigger notifications in Android app ",
                NotificationCompat.PRIORITY_HIGH,
                true,
                getResources().getInteger(R.integer.notificationId),
                PendingIntent.FLAG_UPDATE_CURRENT);

    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }
}
