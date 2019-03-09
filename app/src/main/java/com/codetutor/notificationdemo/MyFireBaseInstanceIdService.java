package com.codetutor.notificationdemo;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFireBaseInstanceIdService extends FirebaseMessagingService {

    private static final String TAG = MyFireBaseInstanceIdService.class.getSimpleName();

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i(TAG," The New token has been generated: "+s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.i(TAG," onMessage Received: "+ remoteMessage.getData());
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }
}
